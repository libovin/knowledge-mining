package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Dict;
import com.hiekn.knowledge.mining.bean.vo.DictFileImport;
import com.hiekn.knowledge.mining.bean.vo.DictQuery;

public interface DictService {

    RestData<Dict> findAll(DictQuery bean);

    Dict findOne(String id);

    void delete(String id);

    Dict modify(String id, Dict dataset);

    Dict add(Dict dataset);

    Dict importDict(DictFileImport fileImport);
}
