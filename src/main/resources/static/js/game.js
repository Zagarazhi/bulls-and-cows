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
let goal = generateNum();
let move=0;

function game() {
    var number = document.getElementById("code").value;
    if(!isNaN(parseInt(number))){
        check(number);
    }
}

//Проверка коров и быков
function check(number) {
    let bulls = 0;
    let cows = 0;
    for(let i = 0; i< 4; i++){
        if(number[i]==goal[i])
            bulls++;
        else if(goal.indexOf(number[i]) >= 0)cows++;
    }
    writeTurn(number, bulls, cows);
    console.log(goal);
    console.log('b '+bulls);
    console.log('c '+cows);
}

function saveGame() {
    let j={
        hiddenNumber: '5678',
        startTime: '2015-02-12 13:51:34',
        wasAttemptsConstraint: false,
        wasTimeConstraint: false,
        success: true,
        attempts: [
            {
                answear: '1234',
                time: 40,
                success: false,
            },
            {
                answear: '4321',
                time: 45,
                success: false,
            },
            {
                answear: '5678',
                time: 30,
                success: true,
            }
        ]
    };

    //отправка json
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify(j);
    xhr.open("POST", '/api/v1/save')
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}

function writeTurn(number, bulls, cows) {
    let table = document.querySelector('.turnsList');
    let newLine = document.createElement('p');
    move++;
    newLine.innerHTML = '<span class="guessed">' +move+'. '+number + ' быки: ' + bulls + '; коровы: ' + cows;
    if(bulls==4) {
        saveGame(move,number);
        newLine.innerHTML = 'Вы выиграли!!! Загаданное число: ' + number.toString().split(',').join('');
    }
    table.appendChild(newLine);
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

function timer(){
    var time = parseInt(document.getElementById("timer").value);
    if(isNaN(time) || time <= 0){
        time = 60;
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

window.onload = function() {
    document.getElementById("code").focus();
}