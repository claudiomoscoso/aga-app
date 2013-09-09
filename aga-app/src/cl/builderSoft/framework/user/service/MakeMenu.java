package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSTag;

public class MakeMenu implements BSTag {
    public String execute(BSServiceData serviceData, String tagString) throws BSException {
        return execute(serviceData.getDocument(), tagString);
    }

    public String execute(Document info, String tagString) {
        StringBuffer out = new StringBuffer();

        out.append("<script type=\"text/javascript\">\n");
        out.append("var OUTLOOKBAR_DEFINITION = {\n");
        out.append("format:{\n");
        out.append("target:'body',\n");
        out.append("blankImage:'public/bsMenu/images/b.gif',\n");
        out.append("rollback:true,\n");
        out.append("animationSteps:7,\n");
        out.append("animationDelay:25,\n");
        out.append("templates:{\n");
        out.append("panel:{\n");
        out
                .append("normal:'<div style=\"background: right top url(public/bsMenu/images/panel_right.gif);\"><div style=\"background: left top no-repeat url(public/bsMenu/images/panel_left.gif); padding: 10px 13px 0 13px; color: #7E7D7D; font: bold 11pt arial; text-align: center; height: 35px;\">{text}</div></div>'\n");
        out.append("},\n");
        out.append("item:{\n");
        out
                .append("normal:'<div style=\"background: top right url(public/bsMenu/images/item_bg_right.gif);\"><div style=\"background: top left no-repeat url(public/bsMenu/images/item_bg_left.gif); height: 92px; text-align: center; padding-top: 9px;\"><img src=\"public/bsMenu/images/{icon}.gif\" height=\"32\" /><table align=\"center\" cellspacing=\"0\" cellpading=\"0\" border=\"0,\" height=\"50\"><tr><td align=\"center\" valign=\"middle\" style=\"font: 8pt tahoma;\">{text}</td></tr></table></div></div>',\n");
        out
                .append("rollovered:'<div style=\"background: top right url(public/bsMenu/images/item_bg_right_r.gif);\"><div style=\"background: top left no-repeat url(public/bsMenu/images/item_bg_left_r.gif); height: 92px; text-align: center; padding-top: 9px;\"><img src=\"public/bsMenu/images/{icon}_r.gif\" width=\"32\" height=\"32\" /><table align=\"center\" cellspacing=\"0\" cellpading=\"0\" border=\"0\" height=\"50\"><tr><td align=\"center\" valign=\"middle\" style=\"font: 8pt tahoma;\">{text}</td></tr></table></div></div>'\n");
        out.append("},\n");
        out.append("upArrow:{\n");
        out.append("normal:'<img src=\"public/bsMenu/images/up.gif\" width=\"27\" height=\"28\" />'\n");
        out.append("},\n");
        out.append("downArrow:{\n");
        out.append("normal:'<img src=\"public/bsMenu/images/down.gif\" width=\"27\" height=\"28\" />'\n");
        out.append("}\n");
        out.append("}\n");
        out.append("},\n");
        out.append("panels:[\n");

        List level1 = info.selectNodes("/Service/Response/Fields/Menu/*");
        //		List level1 = info.selectNodes("/Response/Session[1]/Menu/*");
        List level2 = null;
        Node panel = null;
        Node option = null;
        for (int i = 0; i < level1.size(); i++) {
            panel = (Node) level1.get(i);
            if (panel.selectSingleNode("./@Selected").getText().equals("1")) {
                out.append("{text:\"" + panel.selectSingleNode("./@Label").getText() + "\", url:'',\n");

                level2 = panel.selectNodes("./*");
                out.append("items:[\n");
                for (int j = 0; j < level2.size(); j++) {
                    option = (Node) level2.get(j);
                    if (option.selectSingleNode("./@Selected").getText().equals("1")) {
                        out.append("{text:\"" + option.selectSingleNode("./@Label").getText() + "\", icon:'"
                                + option.selectSingleNode("./@icon").getText() + "', url:'"
                                + option.selectSingleNode("./@url").getText() + "'},\n");
                    }
                }
                out.delete(out.length() - 2, out.length());
                out.append("]\n");
                out.append("},\n");
            }
        }
        out.delete(out.length() - 2, out.length());
        out.append("]\n");
        out.append("}\n");
        out.append("new COOLjsOutlookBar(OUTLOOKBAR_DEFINITION);\n");
        out.append("</script>\n");

        //		BSLog.debug(out.toString());

        return out.toString();
    }

    public Object execute(String parameter, Connection conn, BSExecutor exec) throws BSException {
        return null;
    }

}