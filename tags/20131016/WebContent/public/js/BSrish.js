// BSrish.js
	function save(){
		if(!document.getElementById("isHTML").checked){
			document.getElementById("f").fldContent.value = fldLetterContent.document.body.innerHTML;
		}
		aceptar();
	}

	function toggleHTML(){
	
//	alert(checkElement.checked);
	
		if(document.getElementById("isHTML").checked){
//			iframeToTextArea();
//			document.getElementById("f").fldContent.style.display = '';
			document.getElementById("f").fldContent.value = fldLetterContent.document.body.innerHTML;
//			fldLetterContent.document.style.display = 'none';
			document.getElementById("fldLetterContent").style.display = "none";
			document.getElementById("f").fldContent.style.display = '';
			document.getElementById("f").fldContent.focus();
//			fldLetterContent.document.body.innerHTML = fldLetterContent.document.body.innerText;
		} else {
//			document.getElementById("f").fldContent.style.display = 'none';
//			fldLetterContent.document.body.innerText = fldLetterContent.document.body.innerHTML;
			fldLetterContent.document.body.innerHTML = document.getElementById("f").fldContent.value;
			document.getElementById("fldLetterContent").style.display = "";
			document.getElementById("f").fldContent.style.display = 'none';
			fldLetterContent.focus();
//			document.getElementById("f").fldContent.style.display = 'none';
//			fldLetterContent.style.display = '';
		}
	}

	function toggleHeadFooter(){
		if(document.getElementById("isHeadFooter").checked){
			document.getElementById("fldLetterContent").style.display = "none";
			document.getElementById("f").fldContent.style.display = 'none';
			document.getElementById("divHeadFoot").style.display = "";
//			document.getElementById("divFoot").style.display = "";
			document.getElementById("fldHead").focus();
		} else {
			document.getElementById("fldLetterContent").style.display = "";
			document.getElementById("f").fldContent.style.display = '';
			document.getElementById("divHeadFoot").style.display = "none";
//			document.getElementById("divFoot").style.display = "none";
			toggleHTML();
		}	
	}



	function onLoadPage(){ 
		fldLetterContent.document.designMode="On";
		fldLetterContent.document.open();
		fldLetterContent.document.write(document.getElementById("f").fldContent.value);
		fldLetterContent.document.close();
		fldLetterContent.focus();
		document.getElementById("fldDescription").focus(); 
	}


	function cancelar(){
		document.getElementById("f").bsServiceName.value = "ACO.ListPages";
		document.getElementById("f").submit();
	}

	function aceptar(){ 
		document.getElementById("f").bsServiceName.value = "ACO.SavePage";
		document.getElementById("f").submit();
	}

	function changeFormat(command) {
		var tr = frames.fldLetterContent.document.selection.createRange();
		tr.execCommand(command);
		tr.select();
		frames.fldLetterContent.focus();
	}
		
	function changeSize(selectObject){
		var tr = frames.fldLetterContent.document.selection.createRange();
		tr.execCommand("FontSize", false, selectObject.value);
		tr.select();
		frames.fldLetterContent.focus();
	}
	
	function changeFont(selectObject){
		var tr = frames.fldLetterContent.document.selection.createRange();
		tr.execCommand("FontName", false, selectObject.value);
		tr.select();
		frames.fldLetterContent.focus();
	}




