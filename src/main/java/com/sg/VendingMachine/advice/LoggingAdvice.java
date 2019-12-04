/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.advice;

import com.sg.VendingMachine.dao.VendingMachineAuditDao;
import com.sg.VendingMachine.dao.VendingMachineException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author colby
 */
@Aspect
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    
    public LoggingAdvice(VendingMachineAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Exception ex) throws Throwable{
        Object[] args = jp.getArgs();
        String auditEntry = ex + ": ";
        for (Object currentArg : args) {
          
            auditEntry += currentArg;
            
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
