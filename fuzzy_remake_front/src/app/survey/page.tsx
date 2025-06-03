import questionData from '../../../../common/questions.json'

export type Question = {
    id: number,
    text: string,
    category: string,
    reverse_scored: boolean;
};

export function QuestionList() {
    const questions: Question[] = questionData;

    return (
        <>
            {questions.map((question) => (
                <div className='survey-question' key={question.id}>
                    <h3>
                        {question.text}
                    </h3>

                    <div className='rating-scale'>
                        <span>
                            Never
                        </span>

                        <span>
                            Rarely
                        </span>

                        <span>
                            Sometimes
                        </span>

                        <span>
                            Often
                        </span>

                        <span>
                            Always
                        </span>
                    </div>

                    <div className="radio-group">
                    {Array.from({ length: 5 }, (_, num) => {
                        const currentNum = num + 1;
                        return (
                        <div className="radio-option" key={`q${question.id}_${currentNum}`}>
                            <input
                                type="radio"
                                name={`q${question.id}`}
                                id={`q${question.id}_${currentNum}`}
                                value={currentNum.toString()}
                            />
                            <label htmlFor={`q${question.id}_${currentNum}`}>
                                {currentNum}
                            </label>
                        </div>
                        );
                    })}
                    </div>
                </div>
            ))}
        </>
    )
}

export default function Survey() {
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

                    <form className='survey-form' action="/submit-survey" method='POST'>
                        <QuestionList/>

                        <button className='submit-button'>
                            Submit
                        </button>
                    </form>
                </main>
            </div>
        </div>
    )
}