function numBricks(slope, nCables, k) {
	if(nCables > 0) {
  		return k + numBricks(slope, nCables - 1, height(slope, nCables - 1));
  	}
    
    return k
}

function height(slope, x) {
	return Math.sqrt(Math.pow(slope, 2), Math.pow(50 * x, 2));
}

var ends = Math.round(numBricks(903, 30) / brickHeight) * 2;
var middle = Math.round(numBricks(2417, 94 / brickHeight) * 2;