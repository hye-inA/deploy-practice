# Git 기반 CI/CD 구축 
## 1. Github Actions Runner를 Self-hosted runner 방식으로 구축
### 최적화 방안? 배포 방법 ?  2가지
- 방법 1: Docker Compose 기반 (가장 가볍고 추천!)

~~~
EC2 Instance (t2.micro)
├── GitHub Actions Self-Hosted Runner
├── Docker Compose로 앱 실행
└── 간단한 배포 스크립트
~~~
- 방법 2: K3s + ArgoCD 
~~~
EC2 Instance (t2.micro)
├── GitHub Actions Self-Hosted Runner
├── K3s (경량 Kubernetes)
└── ArgoCD (경량 설정)
~~~


## 2. Github Actions CI + ArgoCD CD 파이프라인 구축
## 3. GitOps 방식으로 Springboot 웹서비스 CI/CD 파이프라인으로 구축 

