"use client";

import { useEffect, useState } from 'react';
import Head from 'next/head';

type SurveyResult = {
  id: number;
  userId: number;
  rawResults: {
    user_id: number;
    hysteria: ScaleLevels;
    hypochondria: ScaleLevels;
    depression: ScaleLevels;
    psychopathy: ScaleLevels;
    paranoia: ScaleLevels;
    psychasthenia: ScaleLevels;
    schizophrenia: ScaleLevels;
    hypomania: ScaleLevels;
    introversion: ScaleLevels;
    processed_at: string;
    version: string;
  };
  createdAt: string;
};

type ScaleLevels = {
  low: number;
  mid: number;
  elevated: number;
  high: number;
  very_high: number;
};

export default function ResultsPage() {
  const [results, setResults] = useState<SurveyResult | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchResults = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/results/get-results');

        if (!response.ok) {
          throw new Error(`Failed to fetch results: ${response.statusText}`);
        }

        const data = await response.json();
        setResults(data);

      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unknown error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchResults();
  }, []);

  const formatNumber = (num: number) => {
    return num.toFixed(4);
  };

  return (
    <div className="container">
      <Head>
        <title>Survey Results</title>
        <link 
          href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" 
          rel="stylesheet"
        />
        <link href="/static/styles.css" rel="stylesheet" />
      </Head>

      <div className="contentWrapper">
        <div className="navbar">
          <a href="/">
            <button className="nav-button">Home</button>
          </a>
          <a href="/about">
            <button className="nav-button">About</button>
          </a>
        </div>

        <main className="contentBox">
          <h1>Psychological Survey Results</h1>

          {loading && <div className="loader">Loading results...</div>}
          
          {error && <div className="error">Error: {error}</div>}
          
          {results && (
            <div className="resultsTableContainer">

              <div className="userId-display">
                User ID: {results.userId}
              </div>
              
              <table className="resultsTable">
                <thead>
                  <tr>
                    <th>Scale</th>
                    <th>Low</th>
                    <th>Mid</th>
                    <th>Elevated</th>
                    <th>High</th>
                    <th>Very High</th>
                  </tr>
                </thead>
                <tbody>
                  {[
                    { name: 'Hysteria', levels: results.rawResults.hysteria },
                    { name: 'Hypochondria', levels: results.rawResults.hypochondria },
                    { name: 'Depression', levels: results.rawResults.depression },
                    { name: 'Psychopathy', levels: results.rawResults.psychopathy },
                    { name: 'Paranoia', levels: results.rawResults.paranoia },
                    { name: 'Psychasthenia', levels: results.rawResults.psychasthenia },
                    { name: 'Schizophrenia', levels: results.rawResults.schizophrenia },
                    { name: 'Hypomania', levels: results.rawResults.hypomania },
                    { name: 'Introversion', levels: results.rawResults.introversion },
                  ].map(({ name, levels }) => (
                    <tr key={name}>
                      <td>
                        <span className="scaleName">{name}</span>
                      </td>
                      <td>{formatNumber(levels.low)}</td>
                      <td>{formatNumber(levels.mid)}</td>
                      <td>{formatNumber(levels.elevated)}</td>
                      <td>{formatNumber(levels.high)}</td>
                      <td>{formatNumber(levels.very_high)}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              
              <div className="processedAt-display">
                Processed at: {new Date(results.rawResults.processed_at).toLocaleString()}
              </div>
            </div>
          )}
        </main>
      </div>
    </div>
  );
}