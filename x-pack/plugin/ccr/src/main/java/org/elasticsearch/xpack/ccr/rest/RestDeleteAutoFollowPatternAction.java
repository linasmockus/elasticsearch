/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ccr.rest;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.xpack.core.ccr.action.DeleteAutoFollowPatternAction.Request;

import java.io.IOException;

import static org.elasticsearch.xpack.core.ccr.action.DeleteAutoFollowPatternAction.INSTANCE;

public class RestDeleteAutoFollowPatternAction extends BaseRestHandler {

    public RestDeleteAutoFollowPatternAction(Settings settings, RestController controller) {
        super(settings);
        controller.registerHandler(RestRequest.Method.DELETE, "/_ccr/auto_follow/{leader_cluster}", this);
    }

    @Override
    public String getName() {
        return "ccr_delete_auto_follow_pattern_action";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest restRequest, NodeClient client) throws IOException {
        Request request = new Request();
        request.setLeaderCluster(restRequest.param("leader_cluster"));
        return channel -> client.execute(INSTANCE, request, new RestToXContentListener<>(channel));
    }

}
