function armies(array) {
  let armies = {};

  for (let input of array) {
    if (input.includes("arrives")) {
      let leader = input.substring(0, input.length - 8);
      armies[leader] = {};
    } else if (input.includes(":")) {
      let leader = input.split(": ")[0];
      let armyName = input.split(": ")[1].split(", ")[0];
      let armyCount = Number(input.split(": ")[1].split(", ")[1]);

      if (armies.hasOwnProperty(leader)) {
        armies[leader][armyName] = armyCount;
      }
    } else if (input.includes("+")) {
      let armyName = input.split(" + ")[0];
      let armyCount = Number(input.split(" + ")[1]);

      let entries = Object.entries(armies);
      for (let entry of entries) {
        let general = entry[0];
        let allArmies = Object.keys(entry[1]);
        if (allArmies.includes(armyName)) {
          armies[general][armyName] += armyCount;
        }
      }
    } else if (input.includes("defeated")) {
      let leader = input.substring(0, input.length - 8).trim();
      if (armies.hasOwnProperty(leader)) {
        delete armies[leader];
      }
    }
  }

  let armiesByCount = {};
  let entries = Object.entries(armies);
  for (let entry of entries) {
    let general = entry[0];
    let armiesOfGeneral = entry[1];

    let sumOfSolders = Object.values(armiesOfGeneral).reduce((acc, count) => {
      return acc + count;
    }, 0);

    armiesByCount[general] = sumOfSolders;

    let entriesOfArmies = Object.entries(armiesOfGeneral);
    let sorted = entriesOfArmies.sort((a, b) => {
      return b[1] - a[1];
    });

    let sortedArmies = sorted.reduce((acc, [k, v]) => {
        acc[k] = v;
        return acc;
      }, {})

    armies[general] = sortedArmies;
  }

  let sortedBySum = Object.entries(armiesByCount).sort((a,b)=>{
    return b[1] - a[1]
  }).reduce((acc, [k, v]) => {
    acc[k] = v;
    return acc;
  }, {})

  for(let key in sortedBySum){
    console.log(`${key}: ${sortedBySum[key]}`);
    
    let armiesOfCurrentGeneral = Object.entries(armies[key]);
    for(let army of armiesOfCurrentGeneral){
        console.log(`>>> ${army[0]} - ${army[1]}`);
    }
  }

}

armies(['Rick Burr arrives', 'Fergus: Wexamp, 30245', 'Rick Burr: Juard, 50000', 'Findlay arrives', 'Findlay: Britox, 34540', 'Wexamp + 6000', 'Juard + 1350', 'Britox + 4500', 'Porter arrives', 'Porter: Legion, 55000', 'Legion + 302', 'Rick Burr defeated', 'Porter: Retix, 3205']);
