// pages/results.tsx
import { useEffect, useState } from 'react';
import Head from 'next/head';
import styles from '../styles/Results.module.css';

type SurveyResult = {
  hysteria: ScaleLevels;
  hypochondria: ScaleLevels;
  depression: ScaleLevels;
  psychopathy: ScaleLevels;
  paranoia: ScaleLevels;
  psychasthenia: ScaleLevels;
  schizophrenia: ScaleLevels;
  hypomania: ScaleLevels;
  introversion: ScaleLevels;
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
        const response = await fetch('/api/get-results');
        if (!response.ok) {
          throw new Error('Failed to fetch results');
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
    <div className={styles.container}>
      <Head>
        <title>Survey Results</title>
        <link 
          href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" 
          rel="stylesheet"
        />
        <link href="/static/styles.css" rel="stylesheet" />
      </Head>

      <div className={styles.contentWrapper}>
        <div className={styles.navbar}>
          <a href="/">
            <button className={styles.navButton}>Home</button>
          </a>
          <a href="/about">
            <button className={styles.navButton}>About</button>
          </a>
        </div>

        <main className={styles.contentBox}>
          <h1>Psychological Survey Results</h1>

          {loading && <div className={styles.loader}>Loading results...</div>}
          
          {error && <div className={styles.error}>Error: {error}</div>}
          
          {results && (
            <div className={styles.resultsTableContainer}>
              <table className={styles.resultsTable}>
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
                    { name: 'Hysteria', levels: results.hysteria },
                    { name: 'Hypochondria', levels: results.hypochondria },
                    { name: 'Depression', levels: results.depression },
                    { name: 'Psychopathy', levels: results.psychopathy },
                    { name: 'Paranoia', levels: results.paranoia },
                    { name: 'Psychasthenia', levels: results.psychasthenia },
                    { name: 'Schizophrenia', levels: results.schizophrenia },
                    { name: 'Hypomania', levels: results.hypomania },
                    { name: 'Introversion', levels: results.introversion },
                  ].map(({ name, levels }) => (
                    <tr key={name}>
                      <td>
                        <span className={styles.scaleName}>{name}</span>
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
            </div>
          )}
        </main>
      </div>
    </div>
  );
}