import Image from "next/image";

export default function Home() {
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

      </div>
      <main className="content-box">

        <h1>
          Mini-Mult Assessment
        </h1>
        <div className="highlight-box">
          <p>
            A shortened 71-item version of the MMPI that maintains clinical validity while reducing administration time by 80% compared to the full 567-item test.
          </p>
        </div>

        <h2>
          Key Characteristics
        </h2>
        <ul className="feature-list">
          <li>
            <strong>
              71 items (original MMPI has 567)
            </strong>
          </li>

          <li>
            <strong>
              20-30 minutes completion time
            </strong>
          </li>

          <li>
            <strong>
              10 clinical scales matching the full MMPI
            </strong>
          </li>

          <li>
            <strong>
              3 validity scales (L, F, K) to detect response biases
            </strong>
          </li>

          <li>
            <strong>
              Same scoring and interpretation as standard MMPI
            </strong>
          </li>
        </ul>

        <h2>
          Common Uses
        </h2>
        <div className="uses-grid">
          <div className="use-card">
            Initial clinical screening
          </div>

          <div className="use-card">
            Research studies
          </div>

          <div className="use-card">
            Brief personality assessment
          </div>

          <div className="use-card">
            Settings requiring rapid evaluation
          </div>
        </div>

        <div className="survey-action">
          <a href="/survey">
            <button className="survey-button">
              Take Survey
            </button>
          </a>
        </div>

        <p className="disclaimer">
          Note: While efficient, the Mini-Mult may miss subtle nuances detected by the full MMPI.
        </p>
      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
      </footer>
    </div>
  );
}
