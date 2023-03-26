function solve() {
  const divChildren = Array.from(document.getElementById("quizzie").children);
  const answerParagraphs = document.querySelectorAll("ul li p");
  const result = document.querySelector('#results li h1')

  for (let i = 0; i < answerParagraphs.length; i += 2) {
    answerParagraphs[i].addEventListener("click", resolve);
    answerParagraphs[i + 1].addEventListener("click", resolve);
  }

  let countRightAnswers = 0;

  function resolve(event) {
    let clickedAnswer = event.target;
    let li = clickedAnswer.parentElement.parentElement;
    let currentSection = li.parentElement.parentElement;
    let indexOfCurrentSection = divChildren.indexOf(currentSection);

    if (indexOfCurrentSection === 1) {
      if (clickedAnswer.textContent === "onclick") {
        countRightAnswers++;
      }
      currentSection.classList.add("hidden");
      currentSection.style.display = 'none'
      divChildren[2].classList.remove("hidden");
      divChildren[2].style.display = 'block'

    } else if (indexOfCurrentSection === 2) {
      if (clickedAnswer.textContent === "JSON.stringify()") {
        countRightAnswers++;
      }
      currentSection.classList.add("hidden");
      currentSection.style.display = 'none'
      divChildren[3].classList.remove("hidden");
      divChildren[3].style.display = 'block'

    } else if (indexOfCurrentSection === 3) {
      if (clickedAnswer.textContent === "A programming API for HTML and XML documents") {
        countRightAnswers++;
      }
      currentSection.classList.add("hidden");
      currentSection.style.display = 'none'

      if (countRightAnswers === 3){
        result.textContent = 'You are recognized as top JavaScript fan!';
      }else {
        result.textContent = `You have ${countRightAnswers} right answers`
      }
      document.querySelector('#quizzie ul#results').style.display = 'block';
    }
  }
}
