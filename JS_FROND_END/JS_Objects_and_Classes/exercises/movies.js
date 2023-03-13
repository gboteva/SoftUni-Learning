function movies(input) {
  class Movie {

    constructor(name) {
      this.name = name;
      this.director = undefined;
      this.date = undefined;
    }
  
  }

  let movies = {};

  for (let i = 0; i < input.length; i++) {
    let tokens = input[i];

    if (tokens.includes("addMovie")) {
      let movieName = tokens.split("addMovie ")[1];
      movies[movieName] = new Movie(movieName);
    }

    if (tokens.includes("directedBy")) {
      let arr = tokens.split(" directedBy ");
      let movieName = arr[0].trim();
      let directorName = arr[1].trim();
      if (movies.hasOwnProperty(movieName)) {
        let currentMovie =  movies[movieName];
        currentMovie.director = directorName;
      }
    }

    if (tokens.includes("onDate")) {
      let arr = tokens.split(" onDate ");
      let movieName = arr[0];
      let date = arr[1];
      if (movies.hasOwnProperty(movieName)) {
        movies[movieName].date = date;
      }
    }
  }

  let entries = Object.entries(movies);
  
  for(let entry of entries){
    if (entry[1].director!==undefined && entry[1].date !==undefined){
        let json = JSON.stringify(entry[1]);
        console.log(json);
    }
  }
}

movies([
  'addMovie The Avengers',
  'addMovie Superman',
  'The Avengers directedBy Anthony Russo',
  'The Avengers onDate 30.07.2010',
  'Captain America onDate 30.07.2010',
  'Captain America directedBy Joe Russo'
  ]
  );
