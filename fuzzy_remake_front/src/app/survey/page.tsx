"use client";

import questionData from '../../../../common/questions.json';
import React, { useState } from 'react';

export type Question = {
    id: number,
    text: string,
    category: string,
    reverse_scored: boolean;
};

export default function Survey() {
    const questions: Question[] = questionData
    const [answers, setAnswers] = useState<Record<number, string>>({})

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()

        const answersArray = Object.entries(answers).map(([questionId, value]) => ({
            questionId: `${questionId}`,
            value: value
        }))

        try {
            const response = await fetch('http://localhost:8080/api/survey/submit-survey', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    answers: answersArray
                }),
            })

            if (response.ok) {
                alert('Answers submitted successfully')
            }
        } catch (error) {
            console.error('Error', error)
        }
    }

    const handleAnswerChange = (questionId: number, value: string) => {
        setAnswers(prev => ({
            ...prev,
            [questionId]: value
        }))
    }

    return (
        <div className="container">
            <div className="container-wrapper">
                <div className="navbar">
                    <a href="/">
                        <button className="nav-button">
                        Home
                        </button>
                    </a>

                    <a href="/about">
                        <button className="nav-button">
                        About
                        </button>
                    </a>
                </div>

                <main className='content-box'>
                    <h1>
                        Psychological Assessment
                    </h1>

                    <div className='disclaimer-box'>
                        Please answer honestly based on your feelings. There are no right or wrong answers.
                    </div>

                    <form className='survey-form' onSubmit={handleSubmit}>
                        {questions.map((question) => (
                            <div className='survey-question' key={question.id}>
                                <h3>
                                    {question.text}
                                </h3>

                                <div className='rating-scale'>
                                    <span>Never</span>

                                    <span>Rarely</span>

                                    <span>Sometimes</span>

                                    <span>Often</span>

                                    <span>Always</span>
                                </div>

                                <div className="radio-group">

                                {[1, 2, 3, 4, 5].map((num) => (
                                    <div className="radio-option" key={`q${question.id}_${num}`}>
                                        <input
                                            type="radio"
                                            name={`q${question.id}`}
                                            id={`q${question.id}_${num}`}
                                            value={num}
                                            onChange={() => handleAnswerChange(question.id, num.toString()) }
                                        />
                                        <label htmlFor={`q${question.id}_${num}`}>
                                            {num}
                                        </label>
                                    </div>
                                ))}

                                </div>
                            </div>
                        ))}

                        <button className='submit-button' type='submit'>
                            Submit
                        </button>
                    </form>
                </main>
            </div>
        </div>
    )
}