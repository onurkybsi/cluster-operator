# cluster-operator

A RESTful API that operates a Kubernetes cluster by given cluster metadata with [_kubeconfig_](https://kubernetes.io/docs/concepts/configuration/organize-cluster-access-kubeconfig/)

## System Requirements
* [Maven](https://maven.apache.org/)
* [JDK/JRE 11](https://www.oracle.com/java/technologies/downloads/#java11)

## Installation

1. Use _Maven_ to build _cluster-operator_

```bash
mvn package
```

2. _cluster-operator_ needs _kubeconfig_ file to communicate with the API server of a Kubernetes cluster. You should assign the value of `KUBECONFIG_FILE_PATH` in `application.properties` which created in the `/target` folder as the path of that file.
3. Finally, you can run _cluster-operator_ via executing command below in the `/target` folder

```bash
java -jar .\cluster-operator-1.0.0-SNAPSHOT.jar
```

## Usage

#### Request

`GET informative/getAllCurrentDeployments`

    curl http://localhost:8080/informative/getAllCurrentDeployments
   
#### Response

    StatusCode        : 200
    StatusDescription :
    Content           : [{"name":"mongo-express","namespaceName":"default"},{"name":"mongodb-deployment","namespaceName":"default"}]
    RawContent        : HTTP/1.1 200
                        Transfer-Encoding: chunked
                        Content-Type: application/json
                        Date: Wed, 19 Jan 2022 14:55:05 GMT
