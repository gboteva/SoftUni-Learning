function solve(speed, area) {

let limit = 0;

  switch (area) {
    case "motorway":
        limit = 130;
      break;
    case "interstate":
        limit = 90;
      break;
    case "city":
        limit = 50;
      break;
    case "residential":
        limit = 20;
      break;
  }

  if (speed <= limit){
    console.log(`Driving ${speed} km/h in a ${limit} zone`);
  }else {
    let diff = speed - limit;
    let status;
    if (diff > 40){
        status = 'reckless driving';
    }else if (diff > 20){
        status = 'excessive speeding';
    }else {
        status = 'speeding';
    }

    console.log(`The speed is ${diff} km/h faster than the allowed speed of ${limit} - ${status}`);
  }

}

solve(40, "city");
solve(21, "residential");
solve(120, "interstate");
solve(200, "motorway");
