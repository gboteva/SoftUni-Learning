function search() {
  let input = document.getElementById("searchText");
  let searchedText = input.value;
  let townsLi = Array.from(document.getElementById("towns").children);
  let foundedMatches = 0;
  for (let li of townsLi) {
    if (li.textContent.includes(searchedText)) {
      li.style.fontWeight = 'bold';
      li.style.textDecoration = "underline";
      foundedMatches++;
    }
  }

  let result = document.getElementById('result');
  result.textContent = foundedMatches + ' matches found'
}
