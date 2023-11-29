package com.yuansu.judicialcase;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import com.yuansu.judicialcase.config.CommonConfig;
import com.yuansu.judicialcase.dao.JudicialCaseES;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = com.yuansu.judicialcase.JudicialCaseApplication.class)
public class ElasticsearchHandlerTest {

    @Autowired
    private ElasticsearchClient client;

    @Test
    public void createTest() throws IOException {
        //写法比RestHighLevelClient更加简洁
        CreateIndexResponse indexResponse = client.indices().create(c -> c.index("user"));
    }

    @Test
    public void deleteTest() throws IOException {
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(d -> d.index(CommonConfig.ELASTIC_SEARCH_INDEX));
        System.out.println(deleteIndexResponse.acknowledged());
    }

    @Test
    public void addDocumentTest() throws IOException {

/*        User user = new User("user1", 10);
        IndexResponse indexResponse = client.index(i -> i
                .index("user")

                //设置id
                .id("1")

                //传入user对象
                .document(user));*/

    }

    @Test
    public void getDocumentTest() throws IOException {
/*        GetResponse<User> getResponse = client.get(g -> g
                        .index("user")
                        .id("1")
                , User.class
        );
        System.out.println(getResponse.source());*/
    }

    @Test
    public void updateDocumentTest() throws IOException {
/*        UpdateResponse<User> updateResponse = client.update(u -> u
                        .index("user")
                        .id("1")
                        .doc(new User("user2", 13))
                , User.class);*/
    }

    @Test
    public void deleteDocumentTest() throws IOException {
        DeleteResponse deleteResponse = client.delete(d -> d
                .index("user")
                .id("1")
        );
        System.out.println(deleteResponse.id());
    }

    @Test
    public void bulkTest() throws IOException {
/*        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", 11));
        userList.add(new User("user2", 12));
        userList.add(new User("user3", 13));
        userList.add(new User("user4", 14));
        userList.add(new User("user5", 15));
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        //遍历添加到bulk中
        for(User user : userList){
            bulkOperationArrayList.add(BulkOperation.of(o->o.index(i->i.document(user))));
        }

        BulkResponse bulkResponse = client.bulk(b -> b.index("user")
                .operations(bulkOperationArrayList));*/

    }

    @Test
    public void searchTest() throws IOException {
        // 模糊查询
        SearchResponse<JudicialCaseES> response8 = client.search(s -> s
                        .index(CommonConfig.ELASTIC_SEARCH_INDEX)
                        .query(q -> q
                                .fuzzy(f -> f
                                        .field("result")
                                        .value("毒品")
                                        .fuzziness("0"))
                        )
                , JudicialCaseES.class);
        System.out.println(response8.took());
        System.out.println(response8.hits().total().value());
        response8.hits().hits().forEach(e -> System.out.println(e.source().toString()));
    }

    @Test
    public void addDocumentTest2() throws IOException {
        JudicialCaseES judicialCaseES = new JudicialCaseES();
        judicialCaseES.setPid(819051);
        judicialCaseES.setFact("云南省安宁市人民检察院指控，2017年7月13日凌晨1时30分许，被告人陈某某在云南省安宁市某某宾馆房间内容留吸毒人员赵某某、孙某某（上述2人已被行政处罚）等人一同吸食毒品，被民警查获，并现场从被告人陈某某随身携带挎包内查获红色片剂状及晶体状毒品可疑物。经称量，被查获毒品可疑物总净重12.41克，经鉴定，上述毒品可疑物均含甲基苯丙胺成分。公诉机关认为，被告人陈某某非法持有毒品甲基苯丙胺，其行为已触犯《中华人民共和国刑法》第三百四十八条之规定，应当以非法持有毒品罪追究其刑事责任。被告人陈某某到案后如实供述犯罪事实，建议对其从轻处罚。被告人陈某某容留二人吸毒，建议酌情从重处罚。公诉机关建议以非法持有毒品罪判处被告人陈某某有期徒刑一年至二年，并处罚金。被告人陈某某对公诉机关的指控无异议，并当庭提交了悔过书。辩护人对公诉机关的指控无异议，但认为被告人非法持有毒品是自己吸食，主观恶性、社会危害程度较小；被告人到案后，能如实供述犯罪事实，依法可以从轻处罚。望法庭考虑上述情节，对被告人从轻处罚。辩护人同时认为，被告人容留他人吸食毒品的情节，与被告人非法持有毒品，没有法律上的关联性，不应作为从重处罚的量刑情节予以考虑。经审理查明的事实与公诉机关指控的事实一致。");
        judicialCaseES.setResult("一、被告人陈某某犯非法持有毒品罪，判处有期徒刑八个月，并处罚金人民币1000元（刑期从判决执行之日起计算，判决执行前先行羁押的，羁押一日折抵刑期一日，即自2017年7月13日起至2018年3月12日止;罚金限判决生效后三个月内缴纳）。二、公安机关扣押的毒品甲基苯丙胺共计12.41克，依法予以没收。");
        judicialCaseES.setCharge(new ArrayList<>() {{
            add("非法持有毒品罪");
        }});
        judicialCaseES.setReason("本院认为，被告人陈某某非法持有毒品甲基苯丙胺共计12.41克，其行为已触犯国家刑律，构成非法持有毒品罪，应依法惩处。被告人陈某某到案后能如实供述犯罪事实，依法可以从轻处罚。公诉机关的指控，事实清楚，证据确实、充分，罪名成立；所提量刑量刑情节，量刑建议，以及辩护人的辩护意见，本院均予以注意。");
        judicialCaseES.setArticle(Arrays.asList(348, 67, 61, 52, 53, 64));
        IndexResponse indexResponse = client.index(i -> i
                .index(CommonConfig.ELASTIC_SEARCH_INDEX)
                //设置id
                .id(String.valueOf(judicialCaseES.getPid()))
                //传入user对象
                .document(judicialCaseES));
    }
/*    @Test
    public void addAll() throws IOException {
        ElasticSearchDataHandler elasticSearchDataHandler = new ElasticSearchDataHandler(client);
        elasticSearchDataHandler.readDataFromFile();
    }
*/
}