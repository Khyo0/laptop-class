package com.gnjk.chat.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gnjk.chat.domain.ChatRoom;
import com.gnjk.chat.domain.Message;

// @Repository : �뒪�봽留곸뿉�꽌 DAO �씤�떇
@Repository
public class MessageDao implements IMessageDao {

	// @Inject(JAVA�젣怨�), @Autowired(�뒪�봽留곸젣怨�) : �쓽議� 愿�怨� �옄�룞 �뿰寃�
	@Inject
	private SqlSession session;

	private static String namespace = "com.gnjk.chat.dao.IMessageDao";
	
	// 梨꾪똿諛� select
	@Override
	public ChatRoom isRoom(ChatRoom vo)throws Exception {
		ChatRoom room = null;
		// sqlSession.selectOne(留ㅽ띁�씠由�.id, 留ㅺ컻蹂��닔)
		//				insert, delete, update, selectList
		// insert, delete, update : �쁺�뼢 諛쏆� �뻾�쓽 媛쒖닔 �젙�닔濡� 諛섑솚
		// selectOne : �꽕�젙�븳 resultType 媛앹껜 諛섑솚(硫붿냼�뱶 寃곌낵媛� 2媛쒖씠�긽�씠硫� �삁�쇅 諛쒖깮)
		// selectList : �꽕�젙�븳 resultType�쓽 List濡� 諛섑솚
		// Map�쑝濡� select 寃곌낵瑜� 諛쏆쑝硫� 而щ읆�씠由�-�궎, �뜲�씠�꽣-媛�
		room = session.selectOne(namespace + ".isRoom", vo);
		System.out.println("isRoom");

		return room;
	}
	
	// 梨꾪똿諛� �깮�꽦, 梨꾪똿諛� insert
	@Override
	public void createRoom(ChatRoom vo) throws Exception {
		System.out.println("createRoom");
		session.insert(namespace + ".createRoom", vo);
	}

	// 硫붿꽭吏� insert
	@Override
	public void insertMessage(Message vo)throws Exception {
		session.insert(namespace + ".insertMessage", vo);
	}
	/*
	 * // 硫붿꽭吏� 由ъ뒪�듃 select
	 * 
	 * @Override public List<Message> getMessageList(String str) throws Exception {
	 * 
	 * return session.selectList(namespace+".getMessageList" , str);
	 * 
	 * }
	 * 
	 * // 梨꾪똿諛� 由ъ뒪�듃 select
	 * 
	 * @Override public List<ChatRoom> getRoomList(String str) throws Exception {
	 * 
	 * return session.selectList(namespace+".getRoomList",str); }
	 * 
	 * // �썡,,,
	 * 
	 * @Override public List<ChatRoom> getRoomList2(String str) throws Exception {
	 * 
	 * return session.selectList(namespace+".getRoomList2" , str); }
	 * 
	 * // 理쒓렐 硫붿꽭吏� select
	 * 
	 * @Override public Message getRecentMessage(String str) throws Exception {
	 * 
	 * return session.selectOne(namespace+".getRecentMessage" , str); }
	 * 
	 * // 硫붿꽭吏� �씫�� �떆媛�
	 * 
	 * @Override public void updateReadTime(int room_idx, String m_idx, String
	 * rm_idx) throws Exception {
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object> ();
	 * 
	 * map.put("room_idx", room_idx); map.put("m_idx", m_idx); map.put("rm_idx",
	 * rm_idx);
	 * 
	 * session.update(namespace+".updateReadTime" , map); }
	 * 
	 * // �씫吏� �븡�� 硫붿꽭吏� select => memberVO�뿉 �꽔�뼱�빞... �븷 �닔�룄.....
	 * 
	 * @Override public int getUnReadCount(int room_idx, String m_idx, String
	 * rm_idx)throws Exception {
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object> ();
	 * 
	 * map.put("room_idx", room_idx); map.put("m_idx", m_idx); map.put("rm_idx",
	 * rm_idx);
	 * 
	 * // Map�쑝濡� select 援щЦ�쓽 寃곌낵瑜� 諛쏅뒗 寃쎌슦, 而щ읆�씠由꾩씠 �궎, �뜲�씠�꽣媛� 媛� return
	 * session.selectOne(namespace+".getUnReadCount" , map);
	 * 
	 * }
	 * 
	 * @Override public int getAllCount(String str) {
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object> ();
	 * 
	 * map.put("rm_idx", str); if(session.selectOne(namespace+".getAllCount" ,map)
	 * ==null) { return 0; }else { return session.selectOne(namespace+".getAllCount"
	 * ,map); }
	 * 
	 * }
	 */


}