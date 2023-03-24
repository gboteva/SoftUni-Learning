function bookShelf(array) {
  class Book {
    constructor(title, author, genre) {
      this.title = title;
      this.author = author;
      this.genre = genre;
    }
  }

  let library = {}; //->key id => value object - key:genre; value:arrayFromBook

  for (let input of array) {
    if (input.includes("->")) {
      let id = input.split(" -> ")[0];
      let shelfGenre = input.split(" -> ")[1];
      if (!library.hasOwnProperty(id)) {
        library[id] = {};
        library[id][shelfGenre] = [];
      }
    } else {
      let title = input.split(": ")[0];
      let author = input.split(": ")[1].split(", ")[0];
      let genre = input.split(": ")[1].split(", ")[1];
      let book = new Book(title, author, genre);

      let shelf = Object.entries(library);
      for (let entry of shelf) {
        let genreOfShelf = Object.keys(entry[1]);
        for (let g of genreOfShelf) {
          if (g === genre) {
            library[entry[0]][g].push(book);
          }
        }
      }
    }
  }

  let shelfByBookCount = {};

  let shelfs = Object.entries(library);
  for (let entry of shelfs) {
    let BooksOnShelf = Object.values(entry[1])[0];
    let countOfBooks = BooksOnShelf.length;
    shelfByBookCount[entry[0]] = countOfBooks;
  }

  let sortedByCount = Object.entries(shelfByBookCount).sort((a, b) => {
    return b[1] - a[1];
  });

  let libraryEntries = Object.entries(library);

  for (let entry of sortedByCount) {
    let id = entry[0];
    let genre = library[id];

    let genreEntries = Object.entries(genre);
    let genreName = genreEntries[0][0];
    let books = genreEntries[0][1];

    console.log(`${id} ${genreName}: ${books.length}`);

    let sortedBooks = books.sort((a, b) => {
      a.title.localeCompare(b.title);
    });

    for (let book of sortedBooks) {
      console.log(`--> ${book.title}: ${book.author}`);
    }
  }
}

bookShelf([
  "1 -> mystery",
  "2 -> sci-fi",
  "Child of Silver: Bruce Rich, mystery",
  "Lions and Rats: Gabe Roads, history",
  "Effect of the Void: Shay B, romance",
  "Losing Dreams: Gail Starr, sci-fi",
  "Name of Earth: Jo Bell, sci-fi",
]);
