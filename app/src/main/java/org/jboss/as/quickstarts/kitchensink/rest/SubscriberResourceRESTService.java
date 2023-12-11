/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.rest;

// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
// import javax.persistence.NoResultException;
// import javax.validation.ConstraintViolation;
// import javax.validation.ConstraintViolationException;
// import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
// import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.as.quickstarts.kitchensink.data.PackageRepository;
// import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.util.Period;
import org.jboss.as.quickstarts.kitchensink.util.Result;
import org.jboss.as.quickstarts.kitchensink.util.Subscription;
import org.jboss.as.quickstarts.kitchensink.model.Subscriber;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/subscribers")
@RequestScoped
public class SubscriberResourceRESTService {

    @Inject
    private PackageRepository repository;




    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result findSubscriber(Subscriber subscriber) {
        Result result = new Result();
        result.subscriptions = new Subscription();
        result.subscriptions.period = new Period();
        result.subscriptions.period.end="01-01-2024";
        result.subscriptions.period.start="01-01-2023";
        result.subscriptions.packages = repository.findAllBySubscriberId(subscriber.id);
        if (result.subscriptions.packages == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return result;
    }
}
