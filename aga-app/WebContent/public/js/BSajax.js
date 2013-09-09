/**
* http://www.w3schools.com/Ajax/Default.Asp
* http://www.w3schools.com/dom/dom_nodetype.asp
* http://www.w3schools.com/dom/dom_node.asp
* http://www.w3schools.com/dom/default.asp
* http://www.w3schools.com/dom/dom_http.asp
* 
*/
function BSbrowser(){ 
	this.ver=navigator.appVersion;
	this.agent=navigator.userAgent;
	this.dom=document.getElementById?1:0;
	this.opera5=(navigator.userAgent.indexOf("Opera")>-1 && document.getElementById)?1:0;
	this.ie5=(this.ver.indexOf("MSIE 5")>-1 && this.dom && !this.opera5)?1:0;
	this.ie6=(this.ver.indexOf("MSIE 6")>-1 && this.dom && !this.opera5)?1:0;
	this.ie7=(this.ver.indexOf("MSIE 7")>-1 && this.dom && !this.opera5)?1:0;
	this.ie8=(this.ver.indexOf("MSIE 8")>-1 && this.dom)?1:0;
	this.ie4=(document.all && !this.dom && !this.opera5)?1:0;
	this.ie=this.ie4||this.ie5||this.ie6||this.ie7||this.ie8;
	this.mac=this.agent.indexOf("Mac")>-1;
	this.ns6=(this.dom && parseInt(this.ver) >= 5) ?1:0; 
	this.ns4=(document.layers && !this.dom)?1:0;
	this.bw=(this.ie6 || this.ie5 || this.ie4 || this.ns4 || this.ns6 || this.opera5 || this.ie7);
	return this;
}

function clearCombo(combo, last){
	var len = combo.options.length;
	for(var i = len; i > last ; i-=1){
		combo.remove(i-1);
	}
}
function addOption(combo, key, desc){
	var browser = new BSbrowser();

	var opt = document.createElement("OPTION");
	opt.innerHTML = desc;
	opt.value = key;
	combo.appendChild(opt); 

/*
	if(browser.ie){
		var opt = document.createElement("OPTION");
		opt.innerHTML = desc;
		opt.value = key;
		combo.appendChild(opt); 
	}else{
		var optionString = "<option value='" + key + "'>" + desc + "</option>";
		new Insertion.Bottom(combo, optionString);
	}	
	*/
}

function getFirstElement(node) {
	var out = node.firstChild;
	while (out.nodeType!=1) {
		out = out.nextSibling;
		if(out==null)
			break;
	}
	return out;
}

function getText(node){
	var out = "";
	var browser = new BSbrowser();
    function getRootElement(doc){
    	var out = doc.firstChild;
    	if(out.nodeType != 1){
    		out = out.nextSibling;
    	}
    	return out;
    }

    if(browser.ie){
		out = node.text;
    } else {
		out = node.textContent;
    }
    return out;
}


function getHttpRequest(){
	var out = null;
	try {
		out = new XMLHttpRequest();
	} catch (e) {
		try {
			out = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				out = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Tu navegador de Internet, no soporta AJAX");
			}
		}
	}
	return out;
}

function getRootElement(doc){
	var out = doc.firstChild;
	if(out.nodeType != 1){
		out = out.nextSibling;
	}
	return out;
}

function haveError(doc){
//	var root = getRootElement(doc);
	var errorNode = selectSingleNode(doc, "/Service/Response/Error/Code");
	//alert('-->'+errorNode);
	var errorText = getText(errorNode);
	
	return errorText.length>0;
}

function showError(doc){
	var errorCodeNode = selectSingleNode(doc, "/Service/Response/Error/Code");
	var errorDescNode = selectSingleNode(doc, "/Service/Response/Error/Description");
	var errorCodeText = getText(errorCodeNode);
	var errorDescText = getText(errorDescNode);

	alert("[" + errorCodeText + "] "+errorDescText);
}


/**
function selectSingleNode(node, path) {
	if(path[0] == '/'){
		path = path.substring(1,path.length);
	}
	var arr = path.split('/');
	for(var i = 1 ; i < arr.length ; i++){
		node = node.getElementsByTagName(arr[i])[0];
	}
	return node;
}
*/

function selectSingleNode(node, path) {
	var browser = new BSbrowser();
	if(browser.ie){
		node = node.selectSingleNode(path);
	}else{		
		if(path[0] == '/'){
			path = path.substring(1,path.length);
		}
		var arr = path.split('/');
		//element.getElementsByTagName('Response')[0];
		//alert(navigator.appVersion + "    --     "+ navigator.userAgent);
		for(var i = 0 ; i < arr.length ; i++){
			node = node.getElementsByTagName(arr[i])[0];
		}
	}	
	return node;
}

function selectNodes(node, path) {
	var browser = new BSbrowser();
	var out = null;
	if(browser.ie){
		node = node.selectNodes(path);
	}else{
		if(path[0] == '/'){
			path = path.substring(1,path.length);
		}
		var arr = path.split('/');
		//element.getElementsByTagName('Response')[0];
		for(var i = 0 ; i < arr.length-1 ; i++){
			node = node.getElementsByTagName(arr[i])[0];
		}
		out = node.getElementsByTagName(arr[i]);
	}	
	return out;
}
function rnd(){
	return Math.floor(Math.random() * 1000 + 1);
}