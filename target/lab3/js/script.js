window.onload = function () {
    window.setInterval(updateTime, 0);
    window.setInterval(checkTime, 11000);
};

function updateTime() {
    let clock = $('#clock');
    if (clock.html() === "00:00:00") {
        checkTime();
    }
}

function checkTime() {
    const now = new Date();
    let clock = $('#clock');
    clock.html(now.toLocaleTimeString());
}

function getX() {
    return document.getElementById("x-parent").children[0].value;
}

function getY() {
    return document.getElementById("y-parent").children[0].children[0].value;
}

function getR() {
    return document.getElementById("r-parent").children[0].value;
}

function setX(value) {
    document.getElementById("x-parent").children[0].value = value;
}

function setY(value) {
    document.getElementById("y-parent").children[0].children[0].value = value;
}

function setXError (xErrorStr) {
    document.getElementById("x-error").innerText = xErrorStr;
}
function setYError (yErrorStr) {
    document.getElementById("y-error").innerText = yErrorStr;
}
function setRError (rErrorStr) {
    document.getElementById("r-error").innerText = rErrorStr;
}

function clearErrorMessages () {
    setXError("");
    setYError("");
    setRError("");
}

clickChecker = function () {
    $('.area').click(function (e) {
        let target = this.getBoundingClientRect();
        let x = e.clientX - target.left;
        let y = e.clientY - target.top;
        let r = $('.r-change').first().val();
        let coeff = 100 / r;
        x = ((x - 150) / coeff).toFixed(3);
        y = -((y - 150) / coeff).toFixed(3);
        setX(x);
        setY(y);
        if (validate()) {
            let checkButton = document.getElementById("btn-check-parent").children[0];
            checkButton.click();
        }
    });
}

drawArea = function () {
    let svg = document.getElementById("svgArea");
    let r = getR();
    let results = document.getElementsByClassName("parameter");
    let j = 0;
    for (let i = results.length / 4; i > 0; i--) {
        let x = results[j++].innerText;
        let y = results[j++].innerText;
        j++;
        let hit = results[j++].innerText;
        let circle = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
        circle.setAttribute('cx', String(x / r * 100 + 150));
        circle.setAttribute('cy', String(150 - (y) / r * 100));
        circle.setAttribute("r", "3");
        if (hit === "Да") {
            circle.setAttribute("fill", "green");
        } else {
            circle.setAttribute("fill", "red");
        }
        svg.appendChild(circle);
    }
}

clickChecker();
drawArea();

function validate() {
    clearErrorMessages();
    return validateX(getX()) && validateY(getY()) && validateR(getR());
}

function validateX(x) {
    if (isNaN(x)) {
        setXError("Введите число!");
        return false;
    }
    if (x <= -5 || x >= 3) {
        setXError("Число не соответствует диапазону! (-5..3)");
        return false;
    }
    if (x.length > 17) {
        setXError("Слишком длинное число!");
        return false;
    }
    return true;
}

function validateY(y) {
    if (isNaN(y)) {
        setYError("Введите число!");
        return false;
    }
    if (y <= -3 || y >= 3) {
        setYError("Число не соответствует диапазону! (-3..3)");
        return false;
    }
    if (y.length > 17) {
        setYError("Слишком длинное число!");
        return false;
    }
    return true;
}

function validateR(r) {
    if (isNaN(r)) {
        setRError("Введите число!");
        return false;
    }
    if (r < 1 || r > 5) {
        setRError("Число не соответствует диапазону! [1..5]");
        return false;
    }
    if (r.length > 17) {
        setRError("Слишком длинное число!");
        return false;
    }
    return true;
}

afterAjaxSuccess = function (data) {
    if (data.status == "success") {
        drawArea();
        clickChecker();
    }
}