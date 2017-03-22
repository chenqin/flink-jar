/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.runtime.jobmanager.JobManager;
import org.apache.flink.runtime.taskmanager.TaskManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>FlinkBootstrap</code> serves as the entry point to Flink as an micro
 * service.
 */
public final class FlinkBootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(FlinkBootstrap.class);

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            throw new IllegalArgumentException("Provide `TaskManager` or `JobManager` parameter with config folder");
        }

        //Load Hadoop S3 wrapper classes, due to ClassNotFound Exception without
        Class.forName("org.apache.flink.runtime.fs.hdfs.HadoopFileSystem");
        Class.forName("org.apache.hadoop.fs.s3a.S3AFileSystem");


        //Verify s3 is accessible
        Configuration conf = new Configuration();
        conf.addResource(new Path("config/hadoop/core-site.xml"));
        conf.addResource(new Path("config/hadoop/hdfs-site.xml"));
        FileSystem fs = FileSystem.get(conf);
        fs.listStatus(new Path("s3://dir"));

        if (args[0].equals("TaskManager")) {
            TaskManager.main(new String[]{
                "--configDir", args[1],
            });
        } else if (args[0].equals("JobManager")) {
            JobManager.main(new String[]{
                "--configDir", args[1],
                "--executionMode", "cluster",
            });
        } else {
            throw new IllegalArgumentException("Unknown parameter `" + args[0] + "`");
        }
    }
}
