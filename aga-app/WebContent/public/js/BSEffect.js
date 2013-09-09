//TODO: Ver como definir constantes de tipo Direction.Forward
var Forward  = 1;
var Backward = 2;


Color = function(){
	this.Red = null;
	this.Green = null;
	this.Blue = null;
	
	this.ToHexString = function(){
		return('#' + [this.Red, this.Green, this.Blue].invoke('toColorPart').join(''));  //depends on prototype 1.5.1
	}
}


BSEffect = function(element){
	this.__element = element;
	this.__interval = null;
	this.__startMilliseconds = null;
	this.__timer = null;
	this.__duration = null;
	this.startRedColor = null;
	this.stopRedColor = null;
	this.startRedColor = null;
	this.duration = null;
	this.__startColor = null;
	this.__stopColor = null;
	this.__direction = null;
};

BSEffect.prototype.drawEffect = function(){
	var isRunning = true;

	var timeElapsed = this.getElapsedMilliseconds();

	this.AnimateHighlight();

	if (timeElapsed > this.__duration){
		isRunning = false;
		clearInterval(this.__timer);
	}
	return isRunning;
};

BSEffect.prototype.Highlight = function(startColor, stopColor, duration){
	console.log('element::' + this.__element);
	this.__duration = duration;
	var actualColor = this.__element.getStyle('backgroundColor');
	if(actualColor == 'transparent')
		actualColor = this.HexColorToRGB(startColor);	

	if(actualColor == this.HexColorToRGB(startColor)){
		__direction = Forward;
	}else{
		__direction = Backward;
	}
	
	var currDate = new Date();
	this.__startMilliseconds = currDate.getTime();
	this.__startColor = startColor;
	this.__stopColor = stopColor;

	var interval = (this.__duration / 80, 10);
	this.__timer = setInterval(function() { this.drawEffect(); }, interval);
};
	


BSEffect.prototype.AnimateHighlight = function(){
	var redColor = 0;
	var greenColor = 0;
	var blueColor = 0;
	var floor = Math.floor;
	var elapsed = this.getElapsedMilliseconds();
	
	var c = new Color();
	
	var startColor = new Color();
	var stopColor  = new Color();
	
	var startColor = this.HexColorToInt(this.__startColor);
	var stopColor  = this.HexColorToInt(this.__stopColor);
	
	if (this.__direction == Forward)
	{
		c.Red   = floor(this.CalculateTransition(elapsed, startColor.Red, stopColor.Red - startColor.Red, this.__duration));
		c.Green = floor(this.CalculateTransition(elapsed, startColor.Green, stopColor.Green - startColor.Green, this.__duration));
		c.Blue  = floor(this.CalculateTransition(elapsed, startColor.Blue, stopColor.Blue - startColor.Blue, this.__duration));
	}
	else if (this.__direction == Backward)
	{
		c.Red   = floor(this.CalculateTransition(elapsed, stopColor.Red, startColor.Red - stopColor.Red, this.__duration));
		c.Green = floor(this.CalculateTransition(elapsed, stopColor.Green, startColor.Green - stopColor.Green, this.__duration));
		c.Blue  = floor(this.CalculateTransition(elapsed, stopColor.Blue, startColor.Blue - stopColor.Blue, this.__duration));
	}

	this.__element.setStyle({backgroundColor: c.ToHexString()});
};


BSEffect.prototype.CalculateTransition = function(time, begin, change, duration){
	if (time > duration) return change+begin;
	return begin + (time / duration) * change;
};

BSEffect.prototype.getElapsedMilliseconds = function (){
	if (this.__startMilliseconds > 0){
		var currDate = new Date();
		return (currDate.getTime() - this.__startMilliseconds);
	}
	return 0;
};
	
BSEffect.prototype.HexColorToInt = function(hexColor){
	var s = new String(hexColor);
	var c = new Color();
	c.Red   = this.HexToInt(s.substr(1,2));
	c.Green = this.HexToInt(s.substr(3,2));
	c.Blue	= this.HexToInt(s.substr(5,2));
	return(c);
};

BSEffect.prototype.HexColorToRGB = function(hexColor){
	var s = new String(hexColor);
	var c = new String('rgb(');
	c += this.HexToInt(s.substr(1,2)) + ', ';
	c += this.HexToInt(s.substr(3,2)) + ', ';
	c += this.HexToInt(s.substr(5,2)) + ')';
	return(c);	
};
	
BSEffect.prototype.HexToInt = function(hexStr){
	return parseInt(hexStr, 16);
};

function closeTooltip(){
	// TODO: Hay que mostrar los <select> para IE6.
	document.getElementById('TOOLTIP_CONTAINER').style.display = "none";
	document.getElementById('TOOLTIP_CONTENT').style.display = "none"
	var cellContainer = document.getElementById('TOOLTIP_CELL_CONTAINER');
//	cellContainer.childElements()[0].remove(); 
}

function showTooltip(divPatients, topBox){
	// TODO: Hay que ocultar los <select> para IE6
	document.getElementById('TOOLTIP_CELL_CONTAINER').innerHTML = document.getElementById(divPatients).innerHTML;
	var tooltipContainer = document.getElementById('TOOLTIP_CONTAINER');
	var tooltipContent = document.getElementById('TOOLTIP_CONTENT');

	
	
//	tooltipContainer.style.height = document.body.offsetHeight;
	tooltipContainer.style.height = document.getElementById("TOOLTIP_LIMIT").offsetTop;
	
	tooltipContainer.style.width = document.body.offsetWidth;
	tooltipContainer.style.display = "";
	
//	tooltipContent.style.height = document.body.offsetHeight;

	if(topBox!=undefined){
		tooltipContent.style.top = topBox;
	}
	
	tooltipContent.style.height = document.body.offsetHeight;
	tooltipContent.style.width = document.body.offsetWidth;
	tooltipContent.style.display = "";
	
	//document.getElementById("_CLOSER_TOOTLTIP_").focus();
	 
	/*
	document.getElementById('TOOLTIP_CONTAINER').setStyle({
		height : document.body.offsetHeight,
		width : document.body.offsetWidth,
		display:''
	});
	
	document.getElementById('TOOLTIP_CONTENT').setStyle({
		height : document.body.offsetHeight,
		width : document.body.offsetWidth,
		display:''
	});
	*/

	
//	document.getElementById('TOOLTIP_CONTAINER').style.display = "";
//	document.getElementById('TOOLTIP_CONTENT').style.display = "";
}
