//
//  client.cpp
//  socket
//
//  Created by Apple on 2017/6/7.
//  Copyright © 2017年 Apple. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cstring>
#include <errno.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <string>

#define MAXLINE 4096

void printError(std::string kind){
    printf("%s socket error: %s (errno: %d)\n",kind.c_str(),strerror(errno),errno);
}

int main(int argc, const char * argv[]){
    int sockfd,n;
    char recvline[4096],sendline[100][4096];
    sockaddr_in servaddr;
    argc = 2;
    argv[1] = "127.0.0.1";
    
    if(argc != 2){
        printf("usage: ./client <ipaddress>\n");
        return 0;
    }
    
    
    
    memset(&servaddr, 0, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(6666);
    if (inet_pton(AF_INET, argv[1], &servaddr.sin_addr) <= 0) {
        printf("inet_pton error for %s\n",argv[1]);
        return 0;
    }
    while(1){
        if ((sockfd = socket(AF_INET, SOCK_STREAM, 0))<0) {
            printError("create");
            return 0;
        }
        
        if (connect(sockfd, (sockaddr*)&servaddr, sizeof(servaddr))<0) {
            printError("connect");
            return 0;
        }else{
            
            printf("send msg to server from port %d: \n",sockfd);
            //    scanf("%d",&n);
            //    for (int i = 0; i<n; i++) {
            scanf("%s",sendline[0]);
            //    }
            if (send(sockfd, sendline[0], strlen(sendline[0]), 0)<0) {
                printError("send msg");
                return 0;
            }
            
            //    for (int i = 0; i<n; i++) {
            //        //fgets(sendline, 4096, stdin);
            //
            //        if (send(sockfd, sendline[i], strlen(sendline[i]), 0)<0) {
            //            printError("send msg");
            //            return 0;
            //        }
            //    }
            close(sockfd);
        }
        //        close(sockfd);
    }
    return 0;
}
