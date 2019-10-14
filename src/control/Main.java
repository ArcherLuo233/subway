package control;

import com.sun.prism.shader.Texture_ImagePattern_AlphaTest_Loader;
import model.*;

import java.io.*;

import java.util.*;

public class Main {

    public Station vtoe(Graph graph,String A) {
        if (!graph.stationMap.containsKey(A))
        {
            Station newst=new Station();
            newst.setName(A);
            graph.stationMap.put(A,newst);
        }
        return graph.stationMap.get(A);
    }
    public void init(Main work,Graph graph,String path) throws IOException {
        path="F:\\"+path;
        String filepath=path;
        FileReader fileReader=new FileReader(filepath);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line=bufferedReader.readLine();
        while (line!=null)
        {
            String A,B;
            A="";
            B="";
            int i=0;
            for (i=0;i<line.length();i++)
            {
                char k=line.charAt(i);
                if (k!=' ')
                {
                    A=A+k;
                }
                else
                {
                    break;
                }
            }
            i++;
            for (;i<line.length();i++)
            {
                char k=line.charAt(i);
                B=B+k;
            }

            if (A.equals("*"))
            {
                Line line1=new Line();
                line1.setName(B);
                graph.lineList.add(line1);
            }
            else
            {
                Line line1=graph.lineList.get(graph.lineList.size()-1);

                Station Ast=work.vtoe(graph,A);
                Station Bst=work.vtoe(graph,B);
                if (line1.getLine().size()==0)
                {
                    line1.getLine().add(Ast.getName());
                    line1.getLine().add(Bst.getName());
                }
                else
                {
                    line1.getLine().add(Bst.getName());
                }
                Edge edge1=new Edge();
                edge1.setLine(line1);
                edge1.setStationB(Bst);
                edge1.setNxt(graph.stationMap.get(A).getFstedge());
                graph.stationMap.get(A).setFstedge(edge1);
                Edge edge2=new Edge();
                edge2.setLine(line1);
                edge2.setStationB(Ast);
                edge2.setNxt(graph.stationMap.get(B).getFstedge());
                graph.stationMap.get(B).setFstedge(edge2);
            }
            line=bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
    }
    public List<Node> bfs(Graph graph,String st,String ed) {
        Station besta = graph.stationMap.get(st);
        Station edsta = graph.stationMap.get(ed);
        List<Node> ans = new ArrayList<Node>();
        List<Node> allpathend = new ArrayList<Node>();
        LinkedList<Node> que = new LinkedList<Node>();
        LinkedList<Node> tmp = new LinkedList<Node>();
        Set<String> inq = new HashSet<String>();
        Node q = new Node();
        q.setSt(besta);
        que.push(q);
        Edge edge = new Edge();
        while (!que.isEmpty()) {
            int size = que.size();
            int flag = 1;
            for (int i=0;i<size;i++)
            {
                Node p = que.poll();
                tmp.push(p);
            }
            for (int i = 0; i < size; i++) {
                Node p = tmp.poll();
                inq.add(p.getSt().getName());
                if (p.getSt().equals(edsta)) {
                    flag = 0;
                    allpathend.add(p);
                    continue;
                }
                Station pp = p.getSt();
                edge = pp.getFstedge();
                while (edge.getStationB() != null) {
                    Station qq = edge.getStationB();
                    if (inq.contains(qq.getName())) {
                        edge = edge.getNxt();
                        continue;
                    }
                    Node nxt = new Node();
                    nxt.setSt(edge.getStationB());
                    nxt.setLine(edge.getLine());
                    nxt.setPre(p);
                    que.push(nxt);
                    edge=edge.getNxt();
                }
            }
            if (flag == 0) break;
        }
        List<Node> ppath = new ArrayList<Node>();
        int hc = 1000000;
        for (Node k : allpathend) {
            int phc = 0;
            String nxtline = k.getLine().getName();
            ppath.add(k);
            while (k.getPre() != null) {
                k = k.getPre();
                if(k.getPre()==null){
                    ppath.add(k);
                    break;
                }
                if (!k.getLine().getName().equals(nxtline)) phc++;
                ppath.add(k);
            }
            if (phc < hc) {
                ans = ppath;
            }
        }
        return ans;
    }
    public void Print(List<Node> ans,String p) {
        FileWriter fw = null;
        String path="F:\\"+p;
        try {

            File f=new File(path);
            fw = new FileWriter(f, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println("一共经过"+ans.size()+"站路");
        pw.println(ans.get(ans.size()-1).getSt().getName());
        for (int i=ans.size()-2;i>=1;i--)
        {
            Node now=ans.get(i);
            Node nxt=ans.get(i-1);
            if (!now.getLine().getName().equals(nxt.getLine().getName()))
            {
                pw.print(now.getSt().getName());
                pw.println("(换乘"+nxt.getLine().getName()+")");
            }
            else
            {
                pw.println(now.getSt().getName());
            }
        }
        pw.println(ans.get(0).getSt().getName());
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Print2(Graph graph,String l,String p) {
        FileWriter fw = null;
        String path="F:\\"+p;
        try {

            File f=new File(path);
            fw = new FileWriter(f, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        for (Line i:graph.lineList)
        {
            if (i.getName().equals(l))
            {
                for (String j:i.getLine())
                {
                    pw.println(j);
                }
                break;
            }
        }
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) throws IOException {
        Main work=new Main();
        Graph graph=new Graph();
        String path="";
        String path2="";



//        String st=scan.next();

//        String ed=scan.next();



        String st="";
        String ed="";
        if ((args.length==7||args.length==6)&&args[0].equals("-map"))
        {
            path=args[1];
            try{
                work.init(work,graph,path);
            }catch (IOException e) {
                System.out.println("请输入正确地铁线路图地址");
            }

            if (args[2].equals("-a"))
            {
                String l=args[3];
                if (args[4].equals("-o"))
                {
                    path2=args[5];
                    work.Print2(graph,l,path2);
                }
                else
                {
                    System.out.println("请按照格式输入运行参数");
                }
            }
            if (args[2].equals("-b"))
            {
                st=args[3];
                ed=args[4];
                List<Node> ans=new ArrayList<Node>();
                ans=work.bfs(graph,st,ed);
                if (args[5].equals("-o"))
                {
                    path2=args[6];
                    work.Print(ans,path2);
                }
                else
                {
                    System.out.println("请按照格式输入运行参数");
                }
            }

        }
        else
        {
            System.out.println("请按照格式输入运行参数");
        }
    }
}
