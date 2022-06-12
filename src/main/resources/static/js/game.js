let onGame = false;
let goal = [];
let move = 0;
let gameObj = {};
let time = 0;
let startTime;
let lastTimeInSeconds = 0;
let wasAttemptsConstraint = false;
let wasTimeConstraint = false;
let success = false;
let maxAttempts = 0;
let maxTime = 0;
let attempts = [];
let out = false;

function start() {
    out = false;
    onGame = true;
    gameObj = {};
    success = false;
    goal = generateNum();
    move = 0;
    let currentDatetime = new Date();
    lastTimeInSeconds = currentDatetime.getTime() / 1000;
    startTime = currentDatetime.getFullYear() + "-" 
        + addLeadingZeros(currentDatetime.getMonth() + 1) + "-" 
        + addLeadingZeros(currentDatetime.getDate()) + " " 
        + addLeadingZeros(currentDatetime.getHours()) + ":" 
        + addLeadingZeros(currentDatetime.getMinutes()) + ":" 
        + addLeadingZeros(currentDatetime.getSeconds());
    wasTimeConstraint = document.getElementById("isTime").checked;
    wasAttemptsConstraint = document.getElementById("isAttempts").checked;
    if(wasTimeConstraint) {
        maxTime = parseInt(document.getElementById("timer").value);
        time = maxTime;
        timer();
    } else {
        maxTime = 0;
        time = maxTime;
    }
    if(wasAttemptsConstraint) {
        maxAttempts = parseInt(document.getElementById("ats").value);
        if(isNaN(maxAttempts)){
            maxAttempts = 15;
        }
        ats();
    } else {
        maxAttempts = 0;
    }
    let btn = document.getElementById("startButton");
    btn.innerHTML = 'сброс';
    btn.onclick = function() { stop(); };
}

function stop(){
    out = true;
    if(move > 0) saveGame();
    let btn = document.getElementById("startButton");
    btn.innerHTML = 'старт';
    btn.onclick = function() { start(); };
}

function generateNum() {
    let number = [];
    while (number.length < 4){
        let newNum = Math.floor(Math.random()*10);
        if(number.indexOf(newNum) < 0){
            number.push(newNum);
        }
    }
    console.log(number);
    return number;
}

function game() {
    if(onGame){
        var number = document.getElementById("code").value;
        if(!isNaN(parseInt(number))){
            check(number);
        }
    }
    emptyCode();
}

function check(number) {
    let bulls = 0;
    let cows = 0;
    for(let i = 0; i < 4; i++){
        if(parseInt(number[i]) === goal[i])
            bulls++;
        else if (goal.indexOf(parseInt(number[i])) >= 0) cows++;
    }
    let currentDatetime = new Date();
    let diff = Math.floor(currentDatetime.getTime() / 1000 - lastTimeInSeconds);
    lastTimeInSeconds = currentDatetime.getTime() / 1000;
    success = bulls === 4;
    attempts.push({
        answear: '' + number,
        time: diff,
        success: bulls === 4,
    })
    writeTurn(number, bulls, cows, diff);
    ats();
}

function saveGame() {
    if(wasTimeConstraint && time <= 0){
        success = false;
    }
    if(wasAttemptsConstraint && move > maxAttempts){
        success = false;
    }
    gameObj.hiddenNumber = goal.join('');
    gameObj.startTime = startTime;
    gameObj.wasAttemptsConstraint = wasAttemptsConstraint;
    gameObj.wasTimeConstraint = wasTimeConstraint;
    gameObj.success = out? false : success;
    if(wasTimeConstraint) gameObj.maxTime = maxTime;
    if(wasAttemptsConstraint) gameObj.maxAttempts = maxAttempts;
    gameObj.attempts = attempts;

    //отправка json
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify(gameObj);
    xhr.open("POST", '/api/v1/save')
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    onGame = false;
    time = 0;
    move = 0;
    let btn = document.getElementById("startButton");
    btn.innerHTML = 'старт';
    btn.onclick = function() { start(); };
}

function writeTurn(number, bulls, cows, diff) {
    let table = document.getElementById('history');
    let newLine = document.createElement('p');
    move++;
    newLine.innerHTML = '<span class="guessed">' +move+'. '+number + ' быки: ' + bulls + '; коровы: ' + cows + '; за время: ' + diff;
    if(bulls == 4) {
        saveGame(move,number);
        if(!out){
            newLine.innerHTML += '<br/>Вы выиграли!!! Загаданное число: ' + number;
        } else {
            newLine.innerHTML += '<br/>Вы проиграли, не выполнив ограничения. Загаданное число: ' + number;
        }
    }
    table.appendChild(newLine);
    newLine.scrollIntoView();
}

function clickPress(event) {
    if (event.keyCode == 13) {
        game();
    }
}

function addCode(key){
	var code = document.getElementById("code").value;
    if(code.length < 4){
        code = code + key;
    }
    document.getElementById("code").value = code;
    document.getElementById("code").focus();
}

function emptyCode(){
	document.getElementById("code").value = "";
    document.getElementById("code").focus();
}

function addLeadingZeros(n) {
    if (n <= 9) {
      return "0" + n;
    }
    return n
  }

function timer(){
    if(isNaN(time) || time <= 0){
        maxTime = 60;
        time = maxTime;
    }
    var timer = setInterval(function(){
        var minuts = Math.floor(time / 60);
        var sec = time - Math.round(minuts * 60);
        document.getElementById('safeTimerDisplay').innerHTML='' + ((minuts < 10)? '0' + minuts : minuts) + ':' + ((sec < 10) ? '0' + sec : sec);
        time--;
        if (time < 0) {
            clearInterval(timer);
        }
    }, 1000);
}

function ats(){
    document.getElementById('attemptDsisplay').innerHTML='' + move + '/' + ((maxAttempts < 1)? '∞':maxAttempts);
}

function back(){
    var code = document.getElementById("code").value;
    if(code.length > 0){
        code = code.slice(0, code.length - 1);
    }
    document.getElementById("code").value = code;
    document.getElementById("code").focus();
}