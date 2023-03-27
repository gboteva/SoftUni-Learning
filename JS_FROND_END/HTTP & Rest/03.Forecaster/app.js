function attachEvents() {
  const BASE_URL_LOCATIONS =
    "http://localhost:3030/jsonstore/forecaster/locations/";
  const URL_TODAY = "http://localhost:3030/jsonstore/forecaster/today/";
  const URL_UPCOMING = "http://localhost:3030/jsonstore/forecaster/upcoming/";

  const submitBtn = document.getElementById("submit");

  const textContainerForecast = document.getElementById("forecast");
  const textContainerToday = document.getElementById("current");
  const textContainerUpcoming = document.getElementById("upcoming");

  submitBtn.addEventListener("click", eventHandler);

  let currentLocationCode = null;

  async function eventHandler() {
    const locationInput = document.getElementById('location');
    let locationInputValue = locationInput.value;

    try {
      let locationsResult = await fetch(BASE_URL_LOCATIONS);
      let locationsData = await locationsResult.json();

     
      for (let location of locationsData) {
        if (location.name === locationInputValue) {
          textContainerForecast.style.display = "block";

          currentLocationCode = location.code;

          //today
          let todayResult = await fetch(URL_TODAY + currentLocationCode);
          let todayData = await todayResult.json();
          const { name, forecast } = todayData;
          let tempContent = `${forecast.low}${String.fromCharCode(176)}/${forecast.high}${String.fromCharCode(176)}`;
          let todayCondition = forecast.condition;

          let divForecast = createElement(
            "div",
            "",
            textContainerToday,
            "",
            "forecasts"
          );
          let spanSymbol = createElement(
            "span",
            "",
            divForecast,
            "",
            "condition, symbol",
            "", ''
          );
          let spanCondition = createElement(
            "span",
            "",
            divForecast,
            "",
            "condition"
          );
          let spanLocation = createElement(
            "span",
            name,
            spanCondition,
            "",
            "forecast-data",
            ""
          );
          let spanTemp = createElement(
            "span",
            tempContent,
            spanCondition,
            "",
            "forecast-data"
          );
          let spanWhether = createElement(
            "span",
            todayCondition,
            spanCondition,
            "",
            "forecast-data"
          );
          spanSymbol.innerHTML = fillSymbol(todayCondition);

          //upcoming
          let forecastResult = await fetch(URL_UPCOMING + currentLocationCode);
          let forecastData = await forecastResult.json();
          
          for (let i = 0; i < forecastData.forecast.length; i++) {
            const forecast = forecastData.forecast[i];
            let tempContentUp = `${forecast.low}${String.fromCharCode(176)}/${forecast.high}${String.fromCharCode(176)}`;
            let upcomingCondition = forecast.condition;
            
            let divContainer = createElement(
              "div",
              "",
              textContainerUpcoming,
              "",
              "forecast-info"
            );
            let spanUpcoming = createElement(
              "span",
              "",
              divContainer,
              "",
              "upcoming"
            );
            let spanSymbolUp = createElement(
              "span",
              "",
              spanUpcoming,
              "",
              "symbol"
            );
            let spanTempUp = createElement(
              "span",
              tempContentUp,
              spanUpcoming,
              "",
              "forecast-data"
            );
            let spanCondUp = createElement(
              "span",
              upcomingCondition,
              spanUpcoming,
              "",
              "forecast-data"
            );

            spanSymbolUp.innerHTML = fillSymbol(upcomingCondition)
          }

          locationInput.value = "";
          break;
        }
      }
    } catch (err) {
      let spanError = document.createElement("span");
      spanError.textContent = "Error";
      textContainerForecast.appendChild(spanError);

      textContainerForecast.style.display = "block";
    }
  }

  function fillSymbol(condition) {
    switch (condition) {
      case "Sunny": return '&#x2600';
      case "Partly sunny": return '&#x26C5';
      case "Overcast": return '&#x2601';
      case "Rain": return '&#x2614';
      case "Degrees": return '&#176';
    }
  }

  function createElement(type, content, parent, id, classes, attributes) {
    let element = document.createElement(type);

    if (content && type !== "input") {
      element.textContent = content;
    }

    if (content && type === "input") {
      element.value = content;
    }

    if (parent) {
      parent.appendChild(element);
    }

    if (id) {
      element.id = id;
    }

    if (classes) {
      let classesToAdd =  classes.split(', ');
      element.classList.add(classesToAdd);
    }

    if (attributes) {
      for (const key in attributes) {
        element.setAttribute(key, attributes[key]);
      }
    }

    return element;
  }
}

attachEvents();
