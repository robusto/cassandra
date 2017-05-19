/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.cassandra.tools.nodetool;

import java.util.Map;

import io.airlift.command.Command;

import org.apache.cassandra.repair.messages.RepairOption;
import org.apache.cassandra.tools.NodeProbe;
import org.apache.cassandra.tools.NodeTool.NodeToolCmd;

@Command(name = "listrepairs", description = "Lists all active repairs.")
public class ListRepairs extends NodeToolCmd
{
    @Override
    protected void execute(NodeProbe probe)
    {
        System.out.println("Repair Details: ");
        for (Map<String, String> repairDesc : probe.getRepairs().values())
        {
            String details = "Repair#" + repairDesc.get("commandNo") +
                             " keyspace: " + repairDesc.get("keyspace") +
                             ", ColumnFamilies: " + repairDesc.get(RepairOption.COLUMNFAMILIES_KEY) +
                             ", parallelism: " + repairDesc.get(RepairOption.PARALLELISM_KEY) +
                             ", primary range: " + repairDesc.get(RepairOption.PRIMARY_RANGE_KEY) +
                             ", incremental: " + repairDesc.get(RepairOption.INCREMENTAL_KEY) +
                             ", job threads: " + repairDesc.get(RepairOption.JOB_THREADS_KEY) +
                             ", dataCenters: " + repairDesc.get(RepairOption.DATACENTERS_KEY) +
                             ", hosts: " + repairDesc.get(RepairOption.HOSTS_KEY) +
                             ", previewKind: " + repairDesc.get(RepairOption.PREVIEW) +
                             ", pull repair: " + repairDesc.get(RepairOption.PULL_REPAIR_KEY) +
                             ", ranges: " + repairDesc.get(RepairOption.RANGES_KEY);
            System.out.println(details);
        }
    }
}
