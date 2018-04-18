package IR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Stack;

import java.lang.Boolean;

import AST.*;
import IR.IRException;
import IR.Temporary;
import MyTypes.*;

public class TempManager {
    public int MAX_TEMP_VALUES = 65535;
    public int temp_num = 0;
    public int labelCount = 0;

    public Stack<ArrayList<Temporary>> temporaryBlocks;
    public HashMap<String, Boolean> available; // id -> inUse
    public HashMap<String, ArrayList<Temporary>> instantiated; // type -> temporaryList
    public ArrayList<Temporary> orderedTempInit;
    public ArrayList<Temporary> currentTemporaries; 
    public ArrayList<Temporary> removed;

    public TempManager(){
        available = new HashMap<String, Boolean>();
        instantiated = new HashMap<String, ArrayList<Temporary>>();
        orderedTempInit = new ArrayList<Temporary>();
        currentTemporaries = new ArrayList<Temporary>();
        temporaryBlocks = new Stack<ArrayList<Temporary>>();
        removed = new ArrayList<Temporary>(); 
        return;
    }

    public void PushTemporaryBlock(){
        temporaryBlocks.push(currentTemporaries);
        currentTemporaries = new ArrayList<Temporary>();
    }

    public void PopTemporaryBlock(){
        if (temporaryBlocks.size() > 0){
            markAvailable(currentTemporaries); 
            currentTemporaries = temporaryBlocks.pop();

            if (removed.size() > 0){
                for (Temporary t : removed) {
                    currentTemporaries.add(t);
                }
                removed.clear();
            }
        }
    }
    
    public void removeFromCurrentTempBlock(Temporary temp){
        if (temp == null) {
            return;
        }

        Temporary t = null;
        int i = 0;

        while (i < currentTemporaries.size()){
            t = currentTemporaries.get(i);
            if (t.id == temp.id){
                break;
            }
            i++;
        }

        if (t == null || temp.id != t.id){
            return;
        }

        currentTemporaries.remove(i);
        removed.add(t);
    }

    public String getLabelID() {
        labelCount++; 
        return String.valueOf(labelCount-1);
    }

    public Temporary getTemporary(String type) {
        // check if temp of type t has been instantiated
        ArrayList<Temporary> tlist = instantiated.get(type);
        if (tlist == null) {
            // if not instantiated
            return null;
        }

        // look through instantiated temps of given type
        // and find one which is not in use
        for (Temporary t : tlist){
            if (available.get(String.valueOf(t.id))){
                // found unused temp of correct type
                // mark in use and return
                available.put(String.valueOf(t.id),false); 
                return t;
            }
        }

        // no empty of given type available
        return null;
    }

    public Temporary getAvailableTemporary(MyType ctype) throws IRException {
        String type = TypeConverter.convertType(ctype);
        Temporary t = getTemporary(type);
        // no temporary exists for current type, so make a new one
        if (t == null){
            if (temp_num >= MAX_TEMP_VALUES){
                throw new IRException("Maximum number of temporaries for this function exceeded.\n");
            }

            t = addTemporary(ctype);
            available.put(String.valueOf(t.id), false);
        }

        return t;
    }

    public Temporary addTemporary(MyType ctype) throws IRException {
        if (temp_num >= MAX_TEMP_VALUES){
            throw new IRException("Maximum number of temporaries for this function exceeded.\n");
        }

        String type = TypeConverter.convertType(ctype);

        if (type.length() == 0){
            return null;
        }
        
        Temporary t = new Temporary(temp_num, ctype);
        ArrayList<Temporary> tlist = instantiated.get(type);
        // no temporary of this type exists
        if (tlist == null) {
            tlist = new ArrayList<Temporary>();
            instantiated.put(type, tlist);
        } 

        tlist.add(t);
        available.put(String.valueOf(t.id), false);
        orderedTempInit.add(t);
        currentTemporaries.add(t);
        temp_num++;
        return t;
    }

    public void markAvailable(Temporary t){
        available.put(String.valueOf(t.id), true);
    }

    public void markAvailable(ArrayList<Temporary> tlist){
        for (Temporary t : tlist){
            markAvailable(t);
            t.indexTempId = -1;
        }
    }
}