package com.mike.NinjaGold;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controller {
	private int ProcessGold(int min, int max, HttpSession session) {
		int gold = (int) session.getAttribute("gold");
		Random rand = new Random();
		int money = rand.nextInt(max - min + 1) + min;
		gold += money;
		session.setAttribute("gold", gold);
		return money;
	}
	private void AddActions(String[] action, HttpSession session) {
		ArrayList<String[]> actions = (ArrayList<String[]>) session.getAttribute("actions");
		String t =  new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date());
		action[1] += " " + t;
		actions.add(action);
		session.setAttribute("actions", actions);
		return;
	}
	@RequestMapping("/")
	public String index(HttpSession session) {
		Integer gold;
		if(session.getAttribute("gold") == null) {
			gold = 0;
			session.setAttribute("gold", gold);
		}else {
			gold = (Integer) session.getAttribute("gold");
		}
		if(session.getAttribute("actions") == null) {
			ArrayList<String[]> actions = new ArrayList<String[]>();
			session.setAttribute("actions", actions);
		}
		if(gold < 0) {
			return "redirect:/prison";
		}
		return "index.jsp";
	}
	@RequestMapping("/process/")
	public String process(HttpSession session, @RequestParam(value="location", required=false) String location) {
		
		if(location.equals("farm")) {
			int gold = ProcessGold(10, 20, session);
			String[] message = {"green", "you earned "+gold+" gold from farm"};
			this.AddActions(message, session);
		} else if(location.equals("cave")) {
			int gold = ProcessGold(5, 15, session);
			String[] message = {"green", "you earned "+gold+" gold from cave"};
			this.AddActions(message, session);
		} else if(location.equals("house")) {
			int gold = ProcessGold(2, 5, session);
			String[] message = {"green", "you earned "+gold+" gold from house"};
			this.AddActions(message, session);
		} else if(location.equals("casino")) {
			int gold = ProcessGold(-50, 50, session);
			if(gold > 0) {
				String[] message = {"green", "you earned "+gold+" gold from casino"};
				this.AddActions(message, session);
			} else if (gold < 0) {
				String[] message = {"red", "you entered casino and lost " + gold + " gold"};
				this.AddActions(message, session);
			} else if (gold == 0) {
				String[] message = {"gray", "you made nothing in casino"};
				this.AddActions(message, session);
			}
			
		} else if(location.equals("spa")) {
			int gold = ProcessGold(-20, -5, session);
			String[] message = {"green", "you spent "+gold+" gold at spa"};
			this.AddActions(message, session);
		}
		Integer g = (Integer) session.getAttribute("gold");
		if(g < 0) {
			return "redirect:/prison";
		}
		return "index.jsp";
	}
	@RequestMapping("/prison")
	public String prison(HttpSession session) {
		if((Integer) session.getAttribute("gold") > 0) {
			return "redirect:/";
		}
		return "prison.jsp";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.removeAttribute("gold");
		session.removeAttribute("actions");
		return "redirect:/";
	}
}