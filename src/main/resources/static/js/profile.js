const games = new XMLHttpRequest();
games.open("GET", "/api/v1/profile_games");
games.send();

const info = new XMLHttpRequest();
info.open("GET", "/api/v1/profile_info");
info.send();

info.onload = function() {
    if (info.status === 200) {
      data = JSON.parse(info.responseText);
      let newLine = document.createElement('p');
      newLine.innerHTML = `Имя: ${data[0]}<br/> Счет: ${data[1]}`;
      document.getElementById('info').appendChild(newLine);
    }
}

games.onload = function() {
    if (games.status === 200) {
      let table = document.getElementById('games');
      data = JSON.parse(games.responseText);
      data.forEach(element => {
        let newTr = document.createElement('tr');
        newTr.innerHTML = 
          `<td scope="col">${element.hiddenNumber}</td>
          <td scope="col">${element.realAttempts}</td>
          <td scope="col">${element.time}</td>
          <td scope="col">${element.startTime}</td>
          <td scope="col">${element.wasTimeConstraint}</td>
          <td scope="col">${element.maxTime === null ? '' : element.maxTime}</td>
          <td scope="col">${element.wasAttemptsConstraint}</td>
          <td scope="col">${element.maxAttempts === null ? '' : element.maxAttempts}</td>
          <td scope="col">${element.success}</td>`
        table.appendChild(newTr);
      });
    }
}