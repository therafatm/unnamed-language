package IR;

import java.lang.Boolean;

import MyTypes.MyType;

public class Temporary {
    
    public int id;
    public MyType type;
    public int indexTempId;
    public Boolean justArray;

    public Temporary(int i, MyType t){
        id = i;
        type = t;
        indexTempId = -1;
        justArray = false;
    }

    public String getType(){
        return TypeConverter.convertType(type);
    }

    public String toString(){
        return !justArray && indexTempId > 0 ? "T" + String.valueOf(id) + "[T" + String.valueOf(indexTempId) + "]" : "T" + String.valueOf(id);
    }

    public void printASMInit(String tabwidth){
        // load stack
        System.out.println(tabwidth + getDefaultLoadInit()); 
        System.out.println(tabwidth + getStoreInstruction() + " " + this.id);
        return;
    }

    public String getDefaultLoadInit(){
        if (this.type.isArray || this.type.t.equals("string")){
            return "aconst_null";
        } else {
            return this.type.t.equals("float") ? "ldc 0.0" : "ldc 0";
        }
    }

    public String getReturnInstruction(){
        if (this.type.isArray || this.type.t.equals("string")){
            return "areturn";
        } else if (this.type.t.equals("float")) {
            return "freturn";
        } else {
            return "ireturn";
        }
    }

    public String getLoadInstruction(){
        if (this.type.isArray || this.type.t.equals("string")){
            return "aload";
        } else if (this.type.t.equals("float")) {
            return "fload";
        } else {
            return "iload";
        }
    }

    public String getArrayLoadInstruction(){
        if (this.type.t.equals("string")){
            return "aaload";
        } else if (this.type.t.equals("float")) {
            return "faload";
        } else if (this.type.t.equals("boolean")) {
            return "baload";
        } else {
            return "iaload";
        }
    }

    public String getStoreInstruction(){
        if  (this.type.isArray || this.type.t.equals("string")){
            return "astore";
        } else if (this.type.t.equals("float")) {
            return "fstore";
        } else {
            return "istore";
        }
    }

    public String getArrayStoreInstruction(){
        if  (this.type.t.equals("string")){
            return "aastore";
        } else if (this.type.t.equals("float")) {
            return "fastore";
        } else if (this.type.t.equals("boolean")) {
            return "bastore";
        } 
        else {
            return "iastore";
        }
    }

    public String getLessThanInstruction(){
        if  (this.type.t.equals("string")){
            return "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I";
        } else if (this.type.t.equals("float")) {
            return "fcmpg";
        } else {
            return "isub";
        }
    }
}