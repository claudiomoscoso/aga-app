BSServiceData = function(){
	this.serviceDataDocument = null;
	var requestFields = new Array();
	var serviceName = "";
	
	this.send = function(){
		var httpRequest = getHttpRequest();
		var url = getURL();
		
		httpRequest.open("GET", url, false);
		httpRequest.send(null);
		
		this.serviceDataDocument = httpRequest.responseXML;
		
	};

	function getURL(){
		var out = "ControlServlet?bsServiceName=" + serviceName;
		
		//alert(requestFields[0][0]);
		
		for(var i = 0 ; i < requestFields.length ; i++){
			out += "&" + requestFields[i][0] + "=" + requestFields[i][1];
		}
		
		
		alert(escape(out));
		
		return out;
	};


	this.getDocument = function(){
		return this.serviceDataDocument;
	};

    this.getServiceName = function(){
    	var serviceNameElement = this.serviceDataDocument.firstChild.getElementsByTagName("Request")[0].getElementsByTagName("ServiceName")[0];
    	return getText(serviceNameElement);
    };
    
    this.setServiceName = function(_serviceName) {serviceName = _serviceName;}
    
    
    this.reset = function() {throw "Metodo no implementado";}
    this.hasError = function() {throw "Metodo no implementado";}
    this.setError = function(ex, errorCode, description) {throw "Metodo no implementado";}
    
    this.hasError = function() {throw "Metodo no implementado";}

    /* R E Q U E S T */
    this.addRequestField = function(name, value) {
    	requestFields[requestFields.length] = new Array(name, value);
	};

    this.setRequestField = function(name, value) {throw "Método no implementado";}
    this.removeRequestField = function(name) {throw "Método no implementado";}
    this.existsRequestField = function(name) {throw "Metodo no implementado";}
    
    this.getRequestFieldString = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldBoolean = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldDouble = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldInteger = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldLong = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldCalendar = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getRequestFieldArray = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.getProcessName = function(){throw "Metodo no implementado";}
    this.setProcessName = function(processName){throw "Metodo no implementado";}

    /* R E S P O N S E */
    this.existsResponseField = function(name){throw "Metodo no implementado";}
    
    this.getResponseNode = function(name){
		var out = null;
    	var element = getRootElement(this.serviceDataDocument);

//alert(element);

			element = element.getElementsByTagName('Response');
			if(element.length > 0){
				element = element[0].getElementsByTagName('Fields');
				if(element.length > 0){
					out = element[0].getElementsByTagName(name);
				} else {
					out = null;
				}
			} else {
				out = null;
			}
			
/*
		if(element.length > 0){
		} else {
			out = null;
		}
*/
/*
		if(element.length>0){

		} else {
			out = null;
		}
		
		element = element.getElementsByTagName(name);
*/

    	return out;
    }

    /* S E S S I O N */
    this.getSessionFieldString = function(fieldName, defaultValue){throw "Metodo no implementado";}
    this.existsSessionField = function(name){throw "Metodo no implementado";}
    this.getUserLogon = function(){throw "Metodo no implementado";}
    this.getSiteName = function(){throw "Metodo no implementado";}
    
}
