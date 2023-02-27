function carWash(commands) {
  let percentage = 0;
  commands.forEach((command) => {
    switch (command) {
      case "soap":
        percentage = percentage + 10;
        break;
      case "water":
        percentage = percentage*1.2;
        break;
      case "vacuum cleaner":
        percentage = percentage * 1.25;
        break;
      case "mud":
        percentage = percentage - (percentage * 0.1);
    }
  });
  console.log(`The car is ${percentage.toFixed(2)}% clean.`);
}

carWash(["soap", "water", "mud", "mud", "water", "mud", "vacuum cleaner"]);
