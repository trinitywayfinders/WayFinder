# Build Instructions and Deploy Instructions for the WayFinder Project

## Requirements
1. A system with the following configuration
```
CPU: 4 (4Ghz)
Memory: 16 GB
Storage: 250 GB
OS: Ubuntu 16.06
```
_The User should have full sudo / root privileges on the system_

2. Docker CE Engine 
`Version: 18.09 or higher`
The setup instruction can be found in the official documentation.
https://docs.docker.com/install/linux/docker-ce/ubuntu/

3. To run the system in a fault tolerant distributed manner, enable docker swarm with the below instructions
```bash
$ docker swarm init
```
An token will be output. Any number of new machines can be added with the token and the following command.
```bash
$ docker swarm join --token <token> <System-IP>:2377
```

4. Oracle JDK 8
The install instructions are as follows
```bash
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```

## Getting the Code
The code in the ZIP file (Team3_WayFinder_Source_Code.zip) can be unpacked on to the system or to get the latest code, use the following command in the home directory of the user.
```bash
$ git clone https://github.com/trinitywayfinders/WayFinder.git
```

## Building the code
Move into the checkedout folder and execute the build script. The commands are as follows.
```bash
$ cd Wayfinder
$ ./buildAll
```

## Deploying the Application
### Dev and Test
The application can be deployed using the following command using Docker Compose for Dev and Test.
```bash
$ docker-compose -f docker-compose.yml up
```

The application can be torn down using the following command
```bash
$ docker-compose -f docker-compose.yml down
```

### Production
In case of production deployment, the required number of replicas can be added to the compose file and the application can be deployed in swarm mode using the following command.
```bash
$ docker stack deploy --compose-file swarm-compose.yml wayfinders
```

The system status can be checked using the following command
```bash
$ docker stack services wayfinders
```

The system can be torn down using the following command
```bash
$ docker stack rm wayfinders
```

## Suggested Configuration in Ubuntu
Since IPv6 is used extensively, it is better to redirect IPv6 traffic to IPv4, please execute the following command in ubuntu.
```bash
$ sudo sysctl net.ipv6.conf.all.forwarding=1
```

## VM Specific Instructions
If the system is deployed on a cloud environment like Google Cloud, Microsoft Azure or Amazon Web Services, ensure to open the following ports of ingress in the security settings of the cloud environment.
```
8100, 8080, 8081, 8084 and 8086
```

# Access the application
The application can be accessed using the below URL structure
```
http://<IP of the Address of the System>:8100/
```



