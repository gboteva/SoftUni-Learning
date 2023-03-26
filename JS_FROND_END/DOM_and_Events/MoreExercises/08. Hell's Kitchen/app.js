function solve() {
  document.querySelector("#btnSend").addEventListener("click", onClick);

  function onClick() {
    const textArea = document.querySelector("textarea");
    let inputArr = JSON.parse(textArea.value);
    const bestParagraph = document.querySelector("#outputs #bestRestaurant p");
    const bestWorkerParagraph = document.querySelector("#outputs #workers p");

    let restaurants = getInfoFromInput(inputArr);
    let bestByAvgSalary = {};

    for (let key in restaurants) {
      let workersList = Object.entries(restaurants[key]);

      let sorted = workersList
        .sort((a, b) => {
          return Number(b[1]) - Number(a[1]);
        })
        .reduce((acc, [key, value]) => {
          acc[key] = value;
          return acc;
        }, {});

      restaurants[key] = sorted;

      let sumOfSalary = workersList.reduce((ac, worker) => {
        return ac + Number(worker[1]);
      }, 0);

      let avgSalary = sumOfSalary / workersList.length;

      bestByAvgSalary[key] = avgSalary;
    }

    bestByAvgSalary = Object.entries(bestByAvgSalary)
                  .sort((a, b) => {
                     return b[1] - a[1];
                  });

    let bestName = bestByAvgSalary[0][0];
    let bestAvgSalary = bestByAvgSalary[0][1];
    let bestSalary = Object.entries(restaurants[bestName])[0][1];

    bestParagraph.textContent = `Name: ${bestName} Average Salary: ${bestAvgSalary.toFixed(2)} Best Salary: ${bestSalary.toFixed(2)}`;

    for (let worker of Object.entries(restaurants[bestName])) {
      bestWorkerParagraph.textContent += `Name: ${worker[0]} With Salary: ${worker[1]} `;
    }

    function getInfoFromInput(inputArr) {
      let restaurants = {};

      for (let input of inputArr) {
        let name = input.split(" - ")[0];
        let workers = input.split(" - ")[1].split(", ");

        if (!restaurants.hasOwnProperty(name)) {
          let workersList = {};

          for (let worker of workers) {
            let currentWorker = worker.split(" ");

            workersList[currentWorker[0]] = Number(currentWorker[1]);
          }
          restaurants[name] = workersList;
        } else {
          for (let worker of workers) {
            let currentWorker = worker.split(" ");

            restaurants[name][currentWorker[0]] = Number(currentWorker[1]);
          }
        }
      }

      return restaurants;
    }
  }
}
