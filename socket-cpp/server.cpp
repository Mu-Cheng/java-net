#include <iostream>
#include <cstdio>
#include <cstring>
#include <errno.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <unistd.h>

#define MAXLINE 4096

void printError(char kind[]){
    printf("%s socket error: %s (errno: %d)\n",kind,strerror(errno),errno);
}

int main(int argc, const char * argv[]) {
    // insert code here...
    int listenfd,connfd;
    sockaddr_in servaddr;
    
    char buff[4096];
    int n;
    if ((listenfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        printError("create");
    }
    
    memset(&servaddr, 0, sizeof(servaddr));
    
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port = htons(6666);
    
    if (bind(listenfd, (sockaddr*)&servaddr, sizeof(servaddr)) == -1) {
        printError("bind");
        return 0;
    }
    
    if (listen(listenfd, 10) == -1) {
        printError("listen");
        return 0;
    }
    
    printf("===============waiting for client's request==============\n");
    while (1) {
        if ((connfd = accept(listenfd, (sockaddr*)NULL, NULL)) == -1) {
            printError("accept");
            continue;
        }
        n = recv(connfd, buff, MAXLINE, 0);
        buff[n] = '\0';
        printf("recv msg from client:\n%s\n",buff);
        close(connfd);
    }
    close(listenfd);

    
    return 0;
}

