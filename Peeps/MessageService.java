package com.gnjk.chat.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gnjk.chat.dao.IMessageDao;
import com.gnjk.chat.domain.Message;

@Service
public class MessageService {

   @Autowired
   private IMessageDao messageDao;

   public void save(HttpServletRequest request) throws IOException {
      // 파라미터 읽기
      int ch_idx = Integer.parseInt(request.getParameter("ch_idx"));
      String m_idx = request.getParameter("m_idx");
      String rm_idx = request.getParameter("rm_idx");
      String ch_time = request.getParameter("ch_time");
      String ch_ms = request.getParameter("ch_ms");
      
      // Dao의 파라미터 만들기
      Message vo = new Message();
      vo.setCh_idx(ch_idx);
      vo.setM_idx(m_idx);
      vo.setRm_idx(rm_idx);
      vo.setCh_time(ch_time);
      vo.setCh_ms(ch_ms);
      
      // Dao의 메소드 호출
      messageDao.send(vo);
   }

}
