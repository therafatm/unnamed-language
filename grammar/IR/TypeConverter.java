package IR;

import MyTypes.*;

public class TypeConverter {

    public static String convertType(MyType ctype){
        String type = "";
        if (ctype.isArray){
            type += "A";
        }
        
        if (ctype.t.equals("int")){
            type += "I";
        } else if (ctype.t.equals("float")){
            type += "F";
        } if (ctype.t.equals("char")){
            type += "C";
        } else if (ctype.t.equals("string")){
            type += "U";
        } else if (ctype.t.equals("boolean")){
            type += "Z";
        } else if (ctype.t.equals("void")){
            type += "V";
        }

        return type;
    }
}