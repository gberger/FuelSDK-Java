/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.internal.ExtractOptions;
import com.exacttarget.fuelsdk.internal.ExtractParameter;
import com.exacttarget.fuelsdk.internal.ExtractRequest;
import com.exacttarget.fuelsdk.internal.ExtractRequestMsg;
import com.exacttarget.fuelsdk.internal.ExtractResponseMsg;
import com.exacttarget.fuelsdk.internal.ExtractResult;
import com.exacttarget.fuelsdk.internal.PartnerAPI;
import com.exacttarget.fuelsdk.internal.Soap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharif.ahmed
 */
public class ETDataExtract {
    private ETClient client;
    private Soap soap;
    public ETDataExtract(){
        try {
            client = new ETClient("fuelsdk.properties");
            //PartnerAPI service = new PartnerAPI();
            soap = client.getSoapConnection().getSoap();            
        } catch (ETSdkException ex) {
            Logger.getLogger(ETDataExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testDataExtract2() throws ETSdkException
    {
        ArrayList<ExtractParameter> extractParameters = new ArrayList();
        
        ExtractParameter extractParam = null;
        extractParam = new ExtractParameter();
        extractParam.setName("DECustomerKey");
        extractParam.setValue("017dce26-b61f-43c2-bb15-0e46de82d177");
        extractParameters.add(extractParam);
    
        extractParam = new ExtractParameter();
        extractParam.setName("HasColumnHeaders");
        extractParam.setValue("true");
        extractParameters.add(extractParam);

        extractParam = new ExtractParameter();
        extractParam.setName("_AsyncID");
        extractParam.setValue("0");
        extractParameters.add(extractParam);

        extractParam = new ExtractParameter();
        extractParam.setName("OutputFileName");
        extractParam.setValue("output_java.csv");
        extractParameters.add(extractParam);

        extractParam = new ExtractParameter();
        String datePattern = "MM/dd/yyyy KK:mm a";
        extractParam = new ExtractParameter();
        extractParam.setName("StartDate");
        Calendar start = Calendar.getInstance();
        start.set(2017, 06, 01, 0, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String cd1 = dateFormat.format(start.getTime());
        extractParam.setValue(cd1);
        extractParameters.add(extractParam);
        
        extractParam = new ExtractParameter();
        extractParam.setName("EndDate");
        Calendar end = Calendar.getInstance();
        end.set(2017, 9, 01, 0, 0);
        String cd2 = dateFormat.format(end.getTime());
        extractParam.setValue(cd2);
        extractParameters.add(extractParam);       
        
        ExtractRequest.Parameters eparams = new ExtractRequest.Parameters();
        eparams.getParameter().addAll(extractParameters);
        
        ExtractRequest request = new ExtractRequest();
        request.setOptions(new ExtractOptions());
        request.setID("30a3fe6a-ea7f-447f-bf67-5b09992dcf5c");
        request.setParameters(eparams);
        
        ExtractRequestMsg erm = new ExtractRequestMsg();
        erm.getRequests().add(request);
        
        ExtractResponseMsg resp = soap.extract(erm);
        System.out.println("req id="+resp.getRequestID());
        System.out.println("status="+resp.getOverallStatus());  
        for(Iterator<ExtractResponseMsg.Results> it = resp.getResults().iterator(); it.hasNext();) {
            ExtractResponseMsg.Results res = it.next();
            System.out.println("res type="+res.getExtractResult());
            //System.out.println("res xml="+res.getResultDetailXML());
        }    
        
        
    }
    
    public void testDataExtract() throws ETSdkException
    {
    
            //ArrayList<ExtractParameter> eparams = new ArrayList<ExtractParameter>();
            ExtractParameter[] eparam = new ExtractParameter[6];
            for(int i=0; i<6; i++)
                eparam[i] = new ExtractParameter();
            
            eparam[0].setName("DECustomerKey");eparam[0].setValue("017dce26-b61f-43c2-bb15-0e46de82d177");
            eparam[1].setName("HasColumnHeaders");eparam[1].setValue("true");
            eparam[2].setName("_AsyncID");eparam[2].setValue("0");
            eparam[3].setName("OutputFileName");eparam[3].setValue("sharif_java.csv");
            eparam[4].setName("StartDate");eparam[4].setValue("06/01/2017 12:00:00 AM");
            eparam[5].setName("EndDate");eparam[5].setValue("08/01/2017 12:00:00 AM");
            
            ExtractRequest.Parameters eparams = new ExtractRequest.Parameters();
            eparams.getParameter().addAll(Arrays.asList(eparam));
            //for(int i=0; i<6; i++)
            //    eparams.getParameter().add(eparam[i]);

            ExtractRequest er = new ExtractRequest();
            er.setID("30a3fe6a-ea7f-447f-bf67-5b09992dcf5c");//extractTypes[extractType];
            er.setParameters(eparams);
            er.setOptions( new ExtractOptions() );
            
            ExtractRequestMsg erm = new ExtractRequestMsg();
            erm.getRequests().add(er);
            
            client.refreshToken();
            Soap s = client.getSoapConnection().getSoap();
            ExtractResponseMsg resp = s.extract(erm);
            
            System.out.println("req id="+resp.getRequestID());
            System.out.println("status="+resp.getOverallStatus());
            for(Iterator<ExtractResponseMsg.Results> it = resp.getResults().iterator(); it.hasNext();) {
                ExtractResponseMsg.Results res = it.next();
                System.out.println("res type="+res.getExtractResult());
                //System.out.println("res xml="+res.getResultDetailXML());
            }    
    }
    
    public static void main(String[] args){
        try {
            ETDataExtract etde = new ETDataExtract();
            etde.testDataExtract2();
        } catch (ETSdkException ex) {
            Logger.getLogger(ETDataExtract.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}