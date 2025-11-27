# CI + Pull 방식 CD (GitOps)
Pull 방식 배포 (ArgoCD가 배포)
Github Actions CI + ArgoCD CD 파이프라인 구축 
~~~yaml
name: CI Pipeline

jobs:
  ci:
    runs-on: self-hosted
    steps:
    - run: ./gradlew build
    - run: docker build -t app:v1.0 .
    - run: docker push app:v1.0
  
  update-manifest:  # ← 매니페스트만 업데이트
    needs: ci
    runs-on: self-hosted
    steps:
    - run: |
        sed -i "s/image: .*/image: app:v1.0/" k8s/deployment.yaml
        git push
~~~

**별도로 ArgoCD 실행 중:**
~~~
ArgoCD가 Git 모니터링
→ deployment.yaml 변경 감지
→ Kubernetes에 자동 배포 (Pull 방식)
~~~

~~~yaml
jobs:
  update-manifest:
    runs-on: self-hosted
    steps:
    - run: sed -i "s/image:.*/image: app:v2/" deployment.yaml
    - run: git push  # 매니페스트만 업데이트
```

**그 후:**
```
ArgoCD가 Git 저장소를 모니터링
→ 변경 감지
→ ArgoCD가 Kubernetes에 배포
```

**특징:**
- GitHub Actions는 매니페스트만 업데이트
- ArgoCD가 **실제 배포**를 수행
- **Pull 방식 CD** (GitOps)
~~~



~~~
### Pull 방식 (ArgoCD/GitOps)
```
┌──────────────┐       ┌──────────────┐
│   GitHub     │──────>│  EC2 Runner  │
│  Repository  │ push  │              │
└──────────────┘       └──────┬───────┘
                              │
                              │ 매니페스트만 업데이트
                              ▼
                    ┌──────────────────┐
                    │  k8s-manifests   │
                    │   Repository     │
                    └────────┬─────────┘
                             │
                             │ 지속적으로 모니터링
                             ▼
                    ┌──────────────────┐
                    │     ArgoCD       │
                    │  (Kubernetes에)  │
                    └────────┬─────────┘
                             │
                             │ 변경 감지 시 자동 배포
                             ▼
                    ┌──────────────────┐
                    │   Kubernetes     │
                    │     Cluster      │
                    └──────────────────┘
~~~