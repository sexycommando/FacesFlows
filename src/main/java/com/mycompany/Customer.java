/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

/**
 *
 * @author stnetadmin
 */
public class Customer implements Serializable {

    @Produces
    @FlowDefinition
    public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {

        //Flowの最初に処理を呼び出されるinitializer
        flowBuilder.initializer("#{customerBean.startMemberRegistration()}");

        String flowId = "customer";
        flowBuilder.id("", flowId);
        flowBuilder.viewNode(flowId, "/" + flowId + "/" + flowId + ".xhtml").markAsStartNode();

        flowBuilder.returnNode("returnFromCustomerFlow")
            .fromOutcome("#{customerBean.returnValue}");

        flowBuilder.inboundParameter("productOrder", "#{flowScope.productOrder}");
        flowBuilder.inboundParameter("priceOrder", "#{flowScope.priceOrder}");
        flowBuilder.inboundParameter("adressLine1Order", "#{flowScope.adressLine1Order}");
        flowBuilder.inboundParameter("adressLine2Order", "#{flowScope.adressLine2Order}");
        flowBuilder.inboundParameter("cityOrder", "#{flowScope.cityOrder}");
        flowBuilder.inboundParameter("stateOrder", "#{flowScope.stateOrder}");
        flowBuilder.inboundParameter("postalCodeOrder", "#{flowScope.postalCodeOrder}");
        flowBuilder.inboundParameter("countryOrder", "#{flowScope.countryOrder}");

        flowBuilder.flowCallNode("callOrder").flowReference("", "order")
            .outboundParameter("firstNameCustomer", "#{customerBean.firstName}")
            .outboundParameter("lastNameCustomer", "#{customerBean.lastName}")
            .outboundParameter("adressLine1Customer", "#{customerBean.adressLine1}")
            .outboundParameter("adressLine2Customer", "#{customerBean.adressLine2}")
            .outboundParameter("cityCustomer", "#{customerBean.city}")
            .outboundParameter("stateCustomer", "#{customerBean.state}")
            .outboundParameter("postalCodeCustomer", "#{customerBean.postalCode}")
            .outboundParameter("countryCustomer", "#{customerBean.country}");
        //Flowの最後に処理を呼び出されるfinalizer
        flowBuilder.finalizer("#{customerBean.endMemberRegistration()}");
        return flowBuilder.getFlow();
    }
}
