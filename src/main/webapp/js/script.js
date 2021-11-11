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
    svg.innerHTML = "<defs>\n" +
        "                        <path id=\"path-0\"\n" +
        "                              d=\"M 235.166 158.391 C 247.43 163.058 259.865 165.851 265.522 171.507 C 268.039 173.65 268.71 170.567 270.019 169.259 L 270.394 169.259 C 271.496 167.054 271.893 166.801 271.893 164.012\"\n" +
        "                              style=\"fill: none;\"/>\n" +
        "                        <path id=\"path-1\"\n" +
        "                              d=\"M 242.286 158.765 C 247.964 158.765 250.103 162.138 251.655 162.138 L 251.655 162.513\"\n" +
        "                              style=\"fill: none;\"/>\n" +
        "                        <path id=\"path-2\"\n" +
        "                              d=\"M 53.779 154.643 L 55.278 158.391 C 56.205 159.503 59.156 163.263 60.899 163.263\"\n" +
        "                              style=\"fill: none;\"/>\n" +
        "                        <path id=\"path-3\"\n" +
        "                              d=\"M 168.457 42.588 L 172.58 45.211 C 175.034 46.438 176.366 47.124 177.826 48.584\"\n" +
        "                              style=\"fill: none;\"/>\n" +
        "                        <path id=\"path-4\"\n" +
        "                              d=\"M 157.589 243.462 L 162.086 247.585 C 163.158 248.657 166.083 250.958 167.333 250.958\"\n" +
        "                              style=\"fill: none;\"/>\n" +
        "                    </defs>\n" +
        "                    <path style=\"fill-opacity: 0.6; fill: rgb(47, 154, 255);\"\n" +
        "                          transform=\"matrix(0, -0.244133, 0.244133, 0, 78.664246, 225.192963)\"\n" +
        "                          d=\"M 103.194 292.2 A 204.806 204.806 0 0 1 308 87.394 L 308 292.2 Z\"\n" +
        "                          bx:shape=\"pie 308 292.2 0 204.806 270 360 1@9dd7fd0f\" bx:origin=\"0.5 0.499702\"/>\n" +
        "                    <path d=\"M 19.119 23.724 L 69.119 73.724 L 19.119 73.724 L 19.119 23.724 Z\"\n" +
        "                          style=\"fill: rgb(47, 154, 255); fill-opacity: 0.6;\"\n" +
        "                          transform=\"matrix(0.000035, 1, -1, 0.000035, 223.723328, 130.880173)\"\n" +
        "                          bx:shape=\"triangle 19.119 23.724 50 50 0 0 1@5265f8de\"/>\n" +
        "                    <rect x=\"50\" y=\"50\" width=\"100\" height=\"100\"\n" +
        "                          style=\"fill: rgb(47, 154, 255); fill-opacity: 0.6;\"/>\n" +
        "                    <line style=\"stroke-opacity: 0.6; stroke: rgb(0, 0, 0);\" x1=\"150\" y1=\"25\" x2=\"150\" y2=\"275\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"147\" y1=\"200\" x2=\"153\"\n" +
        "                          y2=\"200.001\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"147\" y1=\"250\" x2=\"153\" y2=\"250\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, 0.000102, -0.000061)\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"147\" y1=\"50\" x2=\"153\" y2=\"50.001\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"147\" y1=\"100\" x2=\"153\"\n" +
        "                          y2=\"100.001\"/>\n" +
        "                    <line style=\"stroke-opacity: 0.6; stroke: rgb(0, 0, 0);\" x1=\"150\" y1=\"25\" x2=\"150\" y2=\"275\"\n" +
        "                          transform=\"matrix(0, 1, -1, 0, 300, 0)\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"47\" y1=\"150\" x2=\"53\" y2=\"150\"\n" +
        "                          transform=\"matrix(0, 1, -1, 0, 200, 100)\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"97\" y1=\"150\" x2=\"103\" y2=\"150\"\n" +
        "                          transform=\"matrix(0, 1, -1, 0, 250, 50)\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"197\" y1=\"150\" x2=\"203\" y2=\"150\"\n" +
        "                          transform=\"matrix(0, 1, -1, 0, 350, -50)\"/>\n" +
        "                    <line style=\"stroke: rgb(0, 0, 0); stroke-opacity: 0.6;\" x1=\"247\" y1=\"150\" x2=\"253\" y2=\"150\"\n" +
        "                          transform=\"matrix(0, 1, -1, 0, 400, -100)\"/>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -32.9375, 214.443741)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">-R</tspan>\n" +
        "                        <tspan x=\"187.945\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -32.9375, 164.453125)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">-R/2</tspan>\n" +
        "                        <tspan x=\"187.94500732421875\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -143.237503, 104.84375)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">-R</tspan>\n" +
        "                        <tspan x=\"187.945\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -97.4375, 104.953125)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">-R/2</tspan>\n" +
        "                        <tspan x=\"187.94500732421875\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -32.9375, 14.443749)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">R</tspan>\n" +
        "                        <tspan x=\"187.945\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, -32.9375, 64.521873)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">R/2</tspan>\n" +
        "                        <tspan x=\"187.94500732421875\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, 4.2625, 104.921875)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">R/2</tspan>\n" +
        "                        <tspan x=\"187.94500732421875\" dy=\"1em\">​</tspan>\n" +
        "                    </text>\n" +
        "                    <text class=\"unselectable\"\n" +
        "                          style=\"fill: rgb(51, 51, 51); font-family: Arial, sans-serif; font-size: 10px; white-space: pre;\"\n" +
        "                          transform=\"matrix(1, 0, 0, 1, 58.462498, 104.84375)\">\n" +
        "                        <tspan x=\"187.945\" y=\"39.215\">R</tspan>\n" +
        "                        <tspan x=\"187.945\" dy=\"1em\">​</tspan>\n" +
        "                    </text>";
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
        if (x <= 0 && y >= 0 && x >= -r && y <= r) {
            circle.setAttribute("fill", "green");
        } else if (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r / 2, 2)) {
            circle.setAttribute("fill", "green");
        } else if (x >= 0 && y <= 0 && y - x >= (-r / 2)) {
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