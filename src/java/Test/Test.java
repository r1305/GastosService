/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import dao.Dao;

/**
 *
 * @author Lenovo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Dao d=new Dao();
        //System.out.println(d.login("rogger.aburto", "123"));
        System.out.println(d.listarGastos("1"));
    }
    
}
