# 👂🏻 내 목소리로 듣는 블로그 ODO
<div align="center">
<img width=100% alt="스크린샷 2024-03-27 11 04 45" src="https://github.com/KEA3-KeLog/ODO-Server/assets/106463658/4afb5a6c-efcc-45c1-a785-d13c6ce77825">
</div>

<br/>
<br/>
<br/>
<br/>

## 1.프로젝트 소개
ODO는 기존 텍스트 기반의 웹 블로그 플랫폼을 넘어 음성 서비스를 제공하는 플랫폼입니다.
글 요약, TTS(Text To Speech) 기능을 제공하며 서비스 내 상점을 이용하여 블로그 커스터마이징이 가능합니다.
마이페이지를 통해서 사용자의 목소리를 학습시키는 것이 가능하고, 글의 요약, 본문, 블로그 소개 글에 적용할 수 있습니다.

<br/>
<br/>

## 2.팀원 소개 및 역할
<div align="center">
  
| **양권상** | **박상웅** | **안현영** | **홍윤기** |
| :------: |  :------: | :------: | :------: |
| [<img src="https://github.com/KEA3-KeLog/ODO-Server/assets/106463658/d16b8181-1b4b-4a20-9bd7-eea2cdaab85d" height=150 width=150> <br/> @gwon477](https://github.com/gwon477) | [<img src="https://github.com/KEA3-KeLog/ODO-Server/assets/106463658/f6930292-ff58-42e8-9d0b-86409bf407f7" height=150 width=150> <br/> @xxng1](https://github.com/xxng1) | [<img src="https://github.com/KEA3-KeLog/ODO-Client/assets/106463658/54c14813-63e3-45f0-b38a-b87d6cb09038" height=150 width=150> <br/> @An-hyeonyoung](https://github.com/An-hyeonyoung) | [<img src="https://github.com/KEA3-KeLog/ODO-Server/assets/106463658/2cde8149-005a-4aa9-ba5c-05c57d96b59a" height=150 width=150> <br/> @dbsrl1026](https://github.com/dbsrl1026) |
|PM|Backend|Frontend|Backend|

</div>

<br/>
<br/>

## 3.개발 환경
- Front : JS, HTML, React, styled-components, Nginx
- Back-end : SpringBoot : v3.xx, Spring Security, FastAPI
- DataBase : Mysql, Redis
- 버전 및 이슈관리 : Github, Github Issues, Github Project
- 협업 툴 : Notion, JIRA, Google Sheet, Slack
- 서비스 배포 환경 : 가천대학교 On-Promise Cloud
- 디자인 : [Figma](https://www.figma.com/file/PlQdkXvYTQkH0eqLZMF5eK/UI?type=design&mode=design&t=hjtDBHN41Ns6TxYI-0)

<br/>
<br/>

## 4.개발 기간 및 작업관리
### ◻︎ 개발 기간

- 전체 개발 기간 : 2023-09-07 ~ 2023-12-27
- UI 구현 : 2023-09-14 ~ 2023-10-31
- 기능 구현 : 2022-11-01 ~ 2022-12-24

<br>

### ◻︎ 작업 관리
|||
| :------: | :------: |
|<img width="854" alt="image" src="https://github.com/KEA3-KeLog/ODO-Client/assets/106463658/30e70045-7ca9-4b74-8af7-797817b7ec9c">|<img width="767" alt="image" src="https://github.com/KEA3-KeLog/ODO-Client/assets/106463658/071b4321-612f-46b6-837a-f34fedcc6399">|

- 구현 내용을 세부적인 tasks로 분할하고 스프린트를 진행했습니다.
- GitHub Projects와 Issues를 사용하여 진행 상황을 공유했습니다.
- 주간회의를 진행하며 작업 순서와 방향성에 대한 고민을 나누고 Jira를 통해 진행상황을 공유했습니다.
  
<br/>
<br/>

## 5.디렉토리 구조

<br/>
<br/>

## 6.사용 기술 스택

<br/>
<br/>

## 7.시스템 아키텍쳐
### 아키텍쳐 설계도

|System Archtecture|
| :------: | 
|<img width=100% alt="스크린샷 2024-03-27 11 43 59" src="https://github.com/KEA3-KeLog/ODO-Client/assets/106463658/6a5029ab-54e7-4a97-b5ea-a0a8d83518f4">|

- 가천대학교 On-Promise 클라우드에 배포를 진행했습니다.
- 하나의 public 서브넷과 두개의 private 서브넷으로 VPC를 구성했습니다.
- Disaster Recovery 를 고려해 kr-central-2-a, kr-central-2-b 두개의 가용영역으로 나눠 배치해 데이터센터 재난 상황과 같은 사고에 대비되도록 하였습니다.

<br/>

- 인터넷 게이트웨이를 통해 VPC 내부로 접근합니다.
- Public Subnet 내에 가상환경을 구성하고 Nginx를 사용해 정적페이지를 출력합니다.
- 가용성 유지를 위해 외부 로드벨런서(Nginx)를 이용해 부하분산을 하도록 했습니다.
- 내부 로드벨런서를 둬서 Private Subnet에서버로 들어가는 부하를 분산시켰습니다.

<br/>

- 연산량이 가장 많은 읽기(R) 요청만 처리하는 Slave DB와 나머지 요청(C,U,D)을 처리하는 Master DB를 분리하여 전체 DB를 구성하였습니다.
- Master와 Slave는 Mysql replication을 통해 데이터의 변경 이벤트가 발생되면 변경 사항을 Slave DB에 반영해 동기화를 맞춥니다.
- Redis를 사용해 인증/인가에 필요한 토큰을 관리합니다.

<br/>

- GitLab과 Jenkins를 활용해서 배포 파이프라인을 구축했습니다.
- 배포과정을 확인하기 위해 Jenkins와 Slack을 연동했습니다.
- Slack을 통해 배포의 성공여부를 확인할 수 있도록 했습니다.



<br/>
<br/>

## 8.주요 기능
|||
| :------: | :------: |
|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/1df3f012-bcd7-4477-bcfb-244a8e665ed3">|<video width="480" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/38e90ff9-2400-4341-adbf-98183f7819aa">|
|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/0074178a-499a-47a9-8e84-a7b784f4337d">|<video width="480" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/b192da07-ab12-4e46-af62-5e24f3b1e0a0">|
- ODO는 글을 읽어주는 음성서비스를 제공합니다.

|||
| :------: | :------: |
|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/afbd66ae-e7eb-42ed-bccf-0811c52e3550">|<video width="480" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/8a818ea4-76b1-450b-a712-ad200b16bc65">|
- 사용자의 목소리를 학습시킬 수 있습니다.
- 게시글의 요약, 본문 블로그 방문 인사말 등에 음성을 적용이 가능합니다.

|||
| :------: | :------: |
|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/f36d8f1e-fe1f-444d-baf7-091d98aaae5f">|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/722c5e17-534c-4cb6-9831-7bf788ebeebf">|
- 상점과 인벤토리 기능을 통해 원하는 디자인과 기능을 구매하고 사용여부를 결정할 수 있습니다.

|||
| :------: | :------: |
|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/ee1e7ade-6534-467e-b0f4-e1b87809350c">|<img width="480" alt="image" src="https://github.com/KEA3-KeLog/ODO-Server/assets/72259206/65f2f2e5-4efb-4fec-b4f5-b96872ee9feb">|
- 웹 블로그 서비스의 인증/인가, 글 작성, 댓글, 대댓글 작성, 개인 정보 수정 등이 가능합니다.


<br/>
<br/>
<br/>

## 9.개선 목료
### ◻︎ 성능 개선
- TTS서비스 모델 학습에 필요한 명확한 데이터 양 확인 필요.
- 서버 부하 최소화 방안 고려 ex) 내부 로드벨런서 고도화, 이미지 저장 주최 클라인트로 변경, 쿼리 리팩토링.
- DB 병목현상 최소화.

<br/>

### ◻︎ MSA 설계 반영
<div align='center'>
  
|Web Application Server MSA|
| :------: | 
|<img width="781" alt="image" src="https://github.com/KEA3-KeLog/ODO-Client/assets/106463658/e4e3c74b-ca67-405b-a320-ebde509d2b0c">|

</div>

- 서비스내 기능을 중점으로 MSA 아키텍쳐 설계 완료.
- Spring Cloud, Eureka, Spring Config 데모 완료.
- 도메인별 ERD 재구성.
- 도메인별 디렉토리 주고 변경.
- 클라이언트/서버 API 호출 대상 변경
