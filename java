var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var blockSize = 10;
var widthInBlocks = canvas.width / blockSize;
var heightInBlocks = canvas.height / blockSize;
var score = 0;
var snake = {
	segments: [
		{x: 5, y: 5},
		{x: 4, y: 5},
		{x: 3, y: 5}
	],
	direction: "right"
};
var apple = {
	x: Math.floor(Math.random() * (widthInBlocks - 1)) + 1,
	y: Math.floor(Math.random() * (heightInBlocks - 1)) + 1
};

function drawBlock(ctx, position) {
	var x = position.x * blockSize;
	var y = position.y * blockSize;
	ctx.fillRect(x, y, blockSize, blockSize);
}

function drawSnake() {
	ctx.fillStyle = "green";
	for (var i = 0; i < snake.segments.length; i++) {
		drawBlock(ctx, snake.segments[i]);
	}
}

function drawApple() {
	ctx.fillStyle = "red";
	drawBlock(ctx, apple);
}

function updateScore() {
	document.getElementById("score").innerHTML = "Score: " + score;
}

function moveSnake() {
	var head = snake.segments[0];
	var tail = snake.segments.pop();
	var newX = head.x;
	var newY = head.y;

	if (snake.direction === "right") {
		newX++;
	} else if (snake.direction === "left") {
		newX--;
	} else if (snake.direction === "up") {
		newY--;
	} else if (snake.direction === "down") {
		newY++;
	}

	tail.x = newX;
	tail.y = newY;
	snake.segments.unshift(tail);
}

function checkCollision() {
	var head = snake.segments[0];
	if (head.x === apple.x && head.y === apple.y) {
		score++;
		updateScore();
		apple.x = Math.floor(Math.random() * (widthInBlocks - 1)) + 1;
		apple.y = Math.floor(Math.random() * (heightInBlocks - 1)) + 1;
		return false;
	}

	if (head.x < 0 || head.x >= widthInBlocks || head.y < 0 || head.y >= heightInBlocks) {
		return true;
	}

	for (var i = 1; i < snake.segments.length; i++) {
		if (head.x === snake.segments[i].x && head.y === snake.segments[i].y) {
			return true;
		}
	}

	return false;
}

function gameOver() {
	clearInterval(gameLoop);
	alert("Game Over!");
}

function gameLoop() {
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	drawSnake();
	drawApple();
	moveSnake();
	if (checkCollision