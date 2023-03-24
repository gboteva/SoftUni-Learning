function comments(array) {
  let articles = {};
  let users = {};

  //store the input data
  for (let input of array) {
    if (input.split(" ").length === 2 && input.split(" ")[0] === "user") {
      let username = input.split(" ")[1];
      if (!users.hasOwnProperty(username)) {
        users[username] = {};
      }
    } else if (
      input.split(" ").length === 2 &&
      input.split(" ")[0] === "article"
    ) {
      let articleName = input.split(" ")[1];
      if (!articles.hasOwnProperty(articleName)) {
        articles[articleName] = {};
      }
    } else if (input.split(" ").length > 2) {
      let tokens = input.split(": ");
      let userInfo = tokens[0].split(" ");
      let [username, , , articleName] = userInfo;
      let commentTitle = tokens[1].split(", ")[0];
      let commentContent = tokens[1].split(", ")[1];

      if (articles.hasOwnProperty(articleName)) {
        if (users.hasOwnProperty(username)) {
          let usersOfArticle = articles[articleName];
          
          if (!usersOfArticle.hasOwnProperty(username)) {
            usersOfArticle[username] = {};
          }

          usersOfArticle[username][commentTitle] = commentContent;
        }
      }
    }
  }

  //store by count of comments
  let articleByCountOfComments = {};

  let articleAndUsers = Object.entries(articles);

  for (let entry of articleAndUsers) {
    let article = entry[0];
    let users = entry[1];

    //sort users by name in ascending order
    let usersAndComments = Object.entries(users);
    let sortedByName = usersAndComments.sort((a, b) => {
      return a[0].localeCompare(b[0]);
    });

    //convert entries to object and add it to the original article object
    articles[article] = sortedByName.reduce((acc, [k, v]) => {
      acc[k] = v;
      return acc;
    }, {});

    let countOfComments = 0;
    for (let en of usersAndComments) {
      let comments = en[1];
      let arr = Object.keys(comments);
      countOfComments += arr.length;
    }

    articleByCountOfComments[article] = countOfComments;
  }

  //sort by count of comments
  let entries = Object.entries(articleByCountOfComments);
  let sorted = entries.sort((a, b) => {
    return b[1] - a[1];
  });

  //print the result
  for (let art of sorted) {
    //get name from sorted object by comments
    let articleName = art[0];
    console.log(`Comments on ${articleName}`);

    //the other data is taken from article object by key from the sorted articleByCountOfComments
    let users = Object.entries(articles[articleName]);
    for (let user of users) {
      let name = user[0];
      let comments = Object.entries(user[1]);

      for(let comment of comments){
        console.log(
            `--- From user ${name}: ${comment[0]} - ${comment[1]}`
          );
      }  
    }
  }
}

comments([
  "user Mark",
  "article Bobby",
  "Mark posts on Bobby: Is, I do really like them",
  "Mark posts on Bobby: title, Run",
]);
