# knowledge-mining

知识挖掘


请求 配置
```json
{
  "content": "待处理文本",
  "config": [
  {
   "model":"nlp",   // 选择的模型
   "inputSource":"content", // 原文本或者结果
   "tool": "HanNLP", // 选择哪个NLP工具
   "method": "segment",  // nlp 工具方法
   "args":[],     // 参数
   "customDict": []
  },{
    "model": "pattern",
    "inputSource": "content",
    "pattern":"\\d+",  // 表达式
    "type":"matches" //  matches 全部匹配  find 部分匹配
  },{
  "model": "related",
  "inputSource": "content",
  "method": "aminer"
  }]
}
```

args 配置
```json
{
  "nlp":{
    "HanNLP":{
      "segment":["dict","index","shortest","nlp","crf","hmm"],
      "pos": ["crf","hmm"],
      "ner": ["crf","hmm"],
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    },
    "StanfordNLP":{
      "segment":[],
      "pos": null,
      "ner": null,
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    },
    "NLPIR":{
      "segment":[],
      "pos": null,
      "ner": null,
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    },
    "FdNLP":{
      "segment":null,
      "pos": null,
      "ner": null,
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    },
    "LTP":{
      "segment":null,
      "pos": null,
      "ner": null,
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    }
  }
      
}
```


响应结果
```json
{
  "content": "待处理文本",
  "results":{
    "nlp":{
      "segment":["","",""],
      "pos":[],
      "ner":[],
      "keyword":"",
      "summary":"",
      "textClassification":"",
      "counter":[
          {"text":"xxx","count":1},
          {"text":"xxy","count":2}
      ]
    },
    "pattern":{
      "matches":[{"text":"待处理文本","value":false}],
     // "matches":[
     // {"text":"分词1","value":false},
     // {"text":"分词2","value":false},
     // {"text":"分词3","value":false}
     // ],
      "find":[
          {"text":"xxx","start":1,"end":4},
          {"text":"xxx","start":1,"end":4},
          {"text":"xxx","start":1,"end":4}
      ]
    },
    "related":{
      "baidu":{},
      "aminer":{},
      "journal":{},
      "literature":{}
    }
  }
}
```