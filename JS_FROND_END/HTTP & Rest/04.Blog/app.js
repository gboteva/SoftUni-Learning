function attachEvents() {
  const URL_POSTS = "http://localhost:3030/jsonstore/blog/posts";
  const URL_COMMENTS = "http://localhost:3030/jsonstore/blog/comments";

  const btn = document.getElementById("btnLoadPosts");
  const select = document.getElementById("posts");
  const btnView = document.getElementById("btnViewPost");

  const header = document.getElementById("post-title");
  const paragraph = document.getElementById("post-body");
  const ulComments = document.getElementById("post-comments");

  btn.addEventListener("click", loadPostsHandler);
  btnView.addEventListener("click", loadCommentsHandler);

  let bodies = {};

  async function loadPostsHandler() {
    let resultPromise = await fetch(URL_POSTS);
    let objectPosts = await resultPromise.json();

    for (let key in objectPosts) {
      const { body, id, title } = objectPosts[key];
      let option = document.createElement("option");
      option.text = title;
      option.value = key;
      select.appendChild(option);
      bodies[key] = objectPosts[key].body;
    }
  }

  async function loadCommentsHandler() {
    let resultPromise = await fetch(URL_COMMENTS);
    let objectComments = await resultPromise.json();

    let selectedOption = Array.from(select.children)
      .filter(option => {
        if (option.selected){
          return true;
        }
      })[0];

    header.textContent = selectedOption.text;
    paragraph.textContent = bodies[selectedOption.value];

    Object.keys(objectComments)
    .filter(
      (key => {
        if (objectComments[key].postId === selectedOption.value){
          return true;
        }
        
      })
    ).forEach(key =>{
      const li = document.createElement("li");
      li.textContent = objectComments[key].text;
      ulComments.appendChild(li);      
    })

  }
}

attachEvents();
