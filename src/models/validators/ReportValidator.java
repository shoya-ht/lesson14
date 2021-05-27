package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportValidator {
    public static List<String> validate(Report r){
        List<String> errors=new ArrayList<String>();

        String plan_error=_validatePlan(r.getPlan());
                if(!plan_error.equals("")){
                    errors.add(plan_error);
                }
        String content_error=_validateContent(r.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }
        String company_error=_validateCompany(r.getCompany());
        if(!company_error.equals("")){
            errors.add(company_error);
        }
        return errors;
    }
    private static String _validatePlan(String plan){
        if(plan==null||plan.equals("")){
            return "明日の予定を入力してください";
        }
        return "";

    }
    private static String _validateCompany(String company){
        if(company==null||company.equals("")){
            return "取引先を入力してください";
        }
        return "";

    }
    private static String _validateContent(String content){
        if(content==null||content.equals("")){
            return "内容を入力してください";
        }
        return "";

    }

}
