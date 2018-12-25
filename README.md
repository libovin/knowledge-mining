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
    "method": "segment",  // nlp 工具方法
    "init": [],      // 初始化参数
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

算法配置
```json
{
    "nlp":{
      "segment":{
        "dict": ["HanNLP"],
        "index": ["HanNLP"],
        "shortest":["HanNLP"],
        "nlp":["HanNLP"],
        "crf":["HanNLP"],
        "hmm":["HanNLP"],
        "stanford":["StanfordNLP"],
        "NLPir": ["NLPir"],
        "fudan": ["FdNLP"]
      },
      "pos":{
        "crf":["HanNLP"],
        "hmm":["HanNLP"],
        "stanford":["StanfordNLP"],
        "fudan": ["FdNLP"]
      },
      "ner":{
        "crf":["HanNLP"],
        "hmm":["HanNLP"],
        "stanford":["StanfordNLP"],
        "fudan": ["FdNLP"]
      },
      "keyword":{
        "args": "size" 
      },
      "summary":{
        "args": "size" 
      },
      "textClassification":{
          
      }
    }
}
```



配置
```json
{
  "nlp":{
    "segment":{
      "algorithm":{
          "index": ["HanNLP"],
          "shortest":["HanNLP"],
          "nlp":["HanNLP"],
          "crf":["HanNLP"],
          "hmm":["HanNLP"]
      },
      "pos":{
        "crf":["HanNLP"],
        "hmm":["HanNLP"]
      },
      "ner":{
        "crf":["HanNLP"],
        "hmm":["HanNLP"],
        "aa":[
            {"tool": "HanNLP", "args":["人名","地名","机构"]},
            {"tool":"StanfordNLP","args":["LOCATION","GPE","ORGANIZATION","PERSON"]},
            {"tool":"FdNLP","args":["人名","地名","机构"]}
        ]
      },
      "keyword":{
      
      },
      "summary":{
      
      },
      "textClassification":{
      
      }
    },
    
    "HanNLP":{
      "init":["人名","地名","机构"],
      "segment":["dict","index","shortest","nlp","crf","hmm"],
      "pos": ["crf","hmm"],
      "ner": ["crf","hmm"],
      "keyword": "Number",
      "summary": "Number",
      "textClassification": null
    },
    "StanfordNLP":{
      "init":["LOCATION","GPE","ORGANIZATION","PERSON"],
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
      "init":["人名","地名","机构"],
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
      "segment":["词1","词2","词3"],
      "pos":[{"tag":"","description":""}],
      "ner":[{"term":"","type":""}],
      "keyword":["",""],
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


```json

```

```json
{
    "content":"",
    "related":{
        "baidu": {
            "academician": {
            "chartData": [
            {
            "cited_count": "41",
            "h_index": "641",
            "name": "Jeffrey Xu Yu",
            "id": "5d0674d51c78a7b87daf7935f3e93ac3",
            "paper_count": "JX Yu"
            },
            {
            "cited_count": "56",
            "h_index": "769",
            "name": "Francisco Herrera",
            "id": "a54caa20ee94ef6032ebb9fa5badc0f6",
            "paper_count": "F Herrera"
            },
            {
            "cited_count": "401",
            "h_index": "1131",
            "name": "Shashi Shekhar",
            "id": "3e5157a9e96da87fcd9a1af2251b6ef8",
            "paper_count": "S Shekhar"
            },
            {
            "cited_count": "46",
            "h_index": "1070",
            "name": "Christos Faloutsos",
            "id": "923b578acd7bc30111cd281266fa0f22",
            "paper_count": "C Faloutsos"
            },
            {
            "cited_count": "56",
            "h_index": "233",
            "name": "Ngoc Thanh Nguyen",
            "id": "8326ec32d7642ca6e51443ee85cc7fce",
            "paper_count": "NT Nguyen"
            }
            ],
            "intro": "5个深度研究学者",
            "name": "相关学者",
            "authorNum": 5,
            "type": "academician"
            },
            "trend": {
            "chartData": [
            {
            "time": 1970,
            "value": 60
            },
            {
            "time": 1971,
            "value": 24
            },
            {
            "time": 1972,
            "value": 23
            },
            {
            "time": 1973,
            "value": 34
            },
            {
            "time": 1974,
            "value": 31
            },
            {
            "time": 1975,
            "value": 33
            },
            {
            "time": 1976,
            "value": 41
            },
            {
            "time": 1977,
            "value": 49
            },
            {
            "time": 1978,
            "value": 34
            },
            {
            "time": 1979,
            "value": 41
            },
            {
            "time": 1980,
            "value": 27
            },
            {
            "time": 1981,
            "value": 46
            },
            {
            "time": 1982,
            "value": 68
            },
            {
            "time": 1983,
            "value": 66
            },
            {
            "time": 1984,
            "value": 57
            },
            {
            "time": 1985,
            "value": 74
            },
            {
            "time": 1986,
            "value": 77
            },
            {
            "time": 1987,
            "value": 80
            },
            {
            "time": 1988,
            "value": 71
            },
            {
            "time": 1989,
            "value": 86
            },
            {
            "time": 1990,
            "value": 94
            },
            {
            "time": 1991,
            "value": 90
            },
            {
            "time": 1992,
            "value": 91
            },
            {
            "time": 1993,
            "value": 104
            },
            {
            "time": 1994,
            "value": 83
            },
            {
            "time": 1995,
            "value": 106
            },
            {
            "time": 1996,
            "value": 121
            },
            {
            "time": 1997,
            "value": 197
            },
            {
            "time": 1998,
            "value": 250
            },
            {
            "time": 1999,
            "value": 506
            },
            {
            "time": 2000,
            "value": 631
            },
            {
            "time": 2001,
            "value": 841
            },
            {
            "time": 2002,
            "value": 1046
            },
            {
            "time": 2003,
            "value": 1209
            },
            {
            "time": 2004,
            "value": 1503
            },
            {
            "time": 2005,
            "value": 2013
            },
            {
            "time": 2006,
            "value": 2441
            },
            {
            "time": 2007,
            "value": 2757
            },
            {
            "time": 2008,
            "value": 3373
            },
            {
            "time": 2009,
            "value": 3868
            },
            {
            "time": 2010,
            "value": 3984
            },
            {
            "time": 2011,
            "value": 3427
            },
            {
            "time": 2012,
            "value": 3469
            },
            {
            "time": 2013,
            "value": 3383
            },
            {
            "time": 2014,
            "value": 3180
            },
            {
            "time": 2015,
            "value": 3281
            },
            {
            "time": 2016,
            "value": 31
            }
            ],
            "hotYear": 2010,
            "intro": "从1970到2016",
            "name": "研究走势",
            "type": "trend",
            "totalArticle": 43101
            },
            "subject": {
            "chartData": [
            {
            "children": [
            {
              "name": "数据挖掘",
              "weight": "16"
            },
            {
              "name": "关联规则",
              "weight": "3"
            },
            {
              "name": "粗糙集",
              "weight": "2"
            },
            {
              "name": "负关联规则",
              "weight": "2"
            },
            {
              "name": "数据挖掘系统",
              "weight": "2"
            },
            {
              "name": "最大频繁项目集",
              "weight": "2"
            }
            ],
            "name": "计算机科学与技术",
            "weight": 0
            },
            {
            "children": [
            {
              "name": "因素分析",
              "weight": "6"
            },
            {
              "name": "覆膜支架",
              "weight": "1"
            },
            {
              "name": "判別分析",
              "weight": "1"
            },
            {
              "name": "不孕症",
              "weight": "1"
            },
            {
              "name": "尿失禁",
              "weight": "1"
            }
            ],
            "name": "临床医学",
            "weight": 0
            },
            {
            "children": [
            {
              "name": "群集分析",
              "weight": "2"
            },
            {
              "name": "社群網路",
              "weight": "2"
            },
            {
              "name": "概念圖",
              "weight": "1"
            },
            {
              "name": "案例式推理",
              "weight": "1"
            },
            {
              "name": "人工智慧",
              "weight": "1"
            }
            ],
            "name": "土木工程",
            "weight": 0
            },
            {
            "children": [
            {
              "name": "高等教育",
              "weight": "1"
            },
            {
              "name": "高科技",
              "weight": "1"
            },
            {
              "name": "物流管理",
              "weight": "1"
            },
            {
              "name": "调查问卷",
              "weight": "1"
            },
            {
              "name": "深度学习",
              "weight": "1"
            }
            ],
            "name": "教育学",
            "weight": 0
            },
            {
            "children": [
            {
              "name": "价格调整",
              "weight": "2"
            },
            {
              "name": "偏好分析",
              "weight": "1"
            },
            {
              "name": "信用卡",
              "weight": "1"
            },
            {
              "name": "物流中心",
              "weight": "1"
            },
            {
              "name": "大数据",
              "weight": "1"
            }
            ],
            "name": "应用经济学",
            "weight": -2439134096863076400
            },
            {
            "children": [
            {
              "name": "模糊集合",
              "weight": "3"
            },
            {
              "name": "发现过程",
              "weight": "1"
            },
            {
              "name": "影响域",
              "weight": "1"
            },
            {
              "name": "Hilbert空间",
              "weight": "1"
            },
            {
              "name": "结构特征",
              "weight": "1"
            }
            ],
            "name": "数学",
            "weight": 0
            }
            ],
            "domainNum": 6,
            "intro": "6-31-1",
            "name": "学科渗透",
            "type": "subject"
            },
            "organization": {
            "chartData": [
            {
            "name": "University of Minho",
            "value": 36
            },
            {
            "name": "University of Minnesota",
            "value": 35
            },
            {
            "name": "Tsinghua University",
            "value": 29
            },
            {
            "name": "Wuhan University",
            "value": 27
            },
            {
            "name": "Fudan University",
            "value": 25
            },
            {
            "name": "Chinese Academy of Sciences",
            "value": 24
            },
            {
            "name": "Sichuan University",
            "value": 23
            },
            {
            "name": "University of Calgary",
            "value": 21
            },
            {
            "name": "Ming Chuan University",
            "value": 21
            },
            {
            "name": "Carnegie Mellon University",
            "value": 21
            }
            ],
            "organizationNum": 10,
            "intro": "10个高发文量机构",
            "name": "相关机构",
            "type": "organization",
            "totalArticle": 262
            },
            "association": {
            "chartData": [
            {
            "data": [
            {
              "time": "1996",
              "value": "1"
            },
            {
              "time": "2003",
              "value": "1"
            },
            {
              "time": "2006",
              "value": "2"
            },
            {
              "time": "2007",
              "value": "1"
            },
            {
              "time": "2008",
              "value": "1"
            },
            {
              "time": "2009",
              "value": "4"
            },
            {
              "time": "2011",
              "value": "2"
            },
            {
              "time": "2012",
              "value": "2"
            },
            {
              "time": "2014",
              "value": "1"
            },
            {
              "time": "2015",
              "value": "1"
            }
            ],
            "name": "数据挖掘",
            "paperNum": 52658
            },
            {
            "data": [
            {
              "time": "2009",
              "value": "1"
            },
            {
              "time": "2010",
              "value": "2"
            },
            {
              "time": "2011",
              "value": "1"
            },
            {
              "time": "2014",
              "value": "1"
            },
            {
              "time": "2016",
              "value": "1"
            }
            ],
            "name": "因素分析",
            "paperNum": 171000
            },
            {
            "data": [
            {
              "time": "2002",
              "value": "1"
            },
            {
              "time": "2007",
              "value": "1"
            },
            {
              "time": "2009",
              "value": "1"
            },
            {
              "time": "2010",
              "value": "1"
            },
            {
              "time": "2015",
              "value": "1"
            }
            ],
            "name": "集群分析",
            "paperNum": 0
            },
            {
            "data": [
            {
              "time": "2001",
              "value": "1"
            },
            {
              "time": "2007",
              "value": "1"
            },
            {
              "time": "2009",
              "value": "1"
            }
            ],
            "name": "关联规则",
            "paperNum": 11742
            },
            {
            "data": [
            {
              "time": "2006",
              "value": "2"
            },
            {
              "time": "2009",
              "value": "1"
            }
            ],
            "name": "模糊集合",
            "paperNum": 1674
            },
            {
            "data": [
            {
              "time": "2012",
              "value": "2"
            }
            ],
            "name": "二次设备",
            "paperNum": 2097
            },
            {
            "data": [
            {
              "time": "2012",
              "value": "2"
            }
            ],
            "name": "粗糙集",
            "paperNum": 12899
            },
            {
            "data": [
            {
              "time": "1996",
              "value": "1"
            },
            {
              "time": "2006",
              "value": "1"
            }
            ],
            "name": "负关联规则",
            "paperNum": 125
            },
            {
            "data": [
            {
              "time": "2008",
              "value": "1"
            },
            {
              "time": "2011",
              "value": "1"
            }
            ],
            "name": "数据挖掘系统",
            "paperNum": 635
            },
            {
            "data": [
            {
              "time": "2005",
              "value": "1"
            },
            {
              "time": "2008",
              "value": "1"
            }
            ],
            "name": "電子商務",
            "paperNum": 2565
            }
            ],
            "intro": "10-43",
            "name": "关联研究",
            "type": "association"
            }
        }
    }
}
```















































