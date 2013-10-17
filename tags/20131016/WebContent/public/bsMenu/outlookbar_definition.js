var OUTLOOKBAR_DEFINITION = {
	format:{
		target:'body',
		blankImage:'public/bsMenu/images/b.gif',
		rollback:true,
		animationSteps:7,
		animationDelay:50,
		templates:{
			panel:{
				normal:'<div style="background: right top url(public/bsMenu/images/panel_right.gif);"><div style="background: left top no-repeat url(public/bsMenu/images/panel_left.gif); padding: 10px 13px 0 13px; color: #7E7D7D; font: bold 11pt arial; text-align: center; height: 35px;">{text}</div></div>'
			},
			item:{
				normal:'<div style="background: top right url(public/bsMenu/images/item_bg_right.gif);"><div style="background: top left no-repeat url(public/bsMenu/images/item_bg_left.gif); height: 92px; text-align: center; padding-top: 9px;"><img src="public/bsMenu/images/{icon}.gif" height="32" /><table align="center" cellspacing="0" cellpading="0" border="0" height="50"><tr><td align="center" valign="middle" style="font: 8pt tahoma;">{text}</td></tr></table></div></div>',
				rollovered:'<div style="background: top right url(public/bsMenu/images/item_bg_right_r.gif);"><div style="background: top left no-repeat url(public/bsMenu/images/item_bg_left_r.gif); height: 92px; text-align: center; padding-top: 9px;"><img src="public/bsMenu/images/{icon}_r.gif" width="32" height="32" /><table align="center" cellspacing="0" cellpading="0" border="0" height="50"><tr><td align="center" valign="middle" style="font: 8pt tahoma;">{text}</td></tr></table></div></div>'
			},
			upArrow:{
				normal:'<img src="public/bsMenu/images/up.gif" width="27" height="28" />'
			},
			downArrow:{
				normal:'<img src="public/bsMenu/images/down.gif" width="27" height="28" />'
			}
		}
	},
	panels:[
		{text:"Administración", url:'',
			items:[
				{text:"Usuarios", icon:'user', url:'ControlServlet?bsServiceName=USR.listUser'},
				{text:"Grupo", icon:'group', url:'ControlServlet?bsServiceName=USR.listRol'},
				{text:"Permiso de Grupos", icon:'keys', url:'ControlServlet?bsServiceName=USR.permiso&fldRol=1'}/*,
				{text:"Add or Remove Programs", icon:'icon7', url:'dummy.html?id=5'},
				{text:"Appearance and Themes", icon:'icon9', url:'dummy.html?id=6'}*/
			]
		},
		{text:"Remuneración", url:'',
			items:[
				{text:"Empleado", icon:'user', url:''},
				{text:"AFP", icon:'icon4', url:''},
				{text:"Tasas", icon:'icon5', url:''},
				{text:"Emitir", icon:'icon2', url:''}
			]
		}
	]
}
