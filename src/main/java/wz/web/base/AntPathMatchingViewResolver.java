package wz.web.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.XMLParseException;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by zhuowang on 16/10/5.
 */
public class AntPathMatchingViewResolver extends UrlBasedViewResolver {
    private static List<File> cachedFiles;

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView view = super.buildView(viewName);
        String url = findFristMatchView(viewName);
        view.setUrl(url);
        return view;
    }

    protected List<File> getCachedFiles() {
        return cachedFiles;
    }

    protected String findFristMatchView(String viewName) throws Exception {
        String prefix = getPrefix();
        String suffix = getSuffix();
        String webRoot = getServletContext().getRealPath("/");
        String rootPath = webRoot + getPrefixRootPath(prefix);
        try {
            File root = new File(URLDecoder.decode(rootPath, "utf-8"));
            if (cachedFiles == null) {
                cachedFiles = new ArrayList<File>();
                cachedFiles = matchFile(root, new FilenameSuffixFilter(suffix), cachedFiles);
            }
            PathMatcher matcher = new AntPathMatcher();
            String name = prefix + viewName + suffix;
            String patten = name;
            for (File file : cachedFiles) {
                String absolutePath = file.getAbsolutePath().replace(webRoot,
                        File.separator);
                absolutePath = absolutePath.replace(File.separator, "//");
                if (matcher.match(patten, absolutePath)) {
                    return absolutePath;
                }
            }
            throw new FileNotFoundException("no match " + name + " view!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected List<File> matchFile(File root, FilenameFilter filter,
                                   List<File> results) {
        if (root.isDirectory()) {
            File[] children = root.listFiles();
            for (File file : children) {
                if (file.isDirectory()) {
                    results = matchFile(file, filter, results);
                } else {
                    if (filter.accept(file, file.getName())) {
                        results.add(file);
                    }
                }
            }
        } else {
            if (filter.accept(root, root.getName())) {
                results.add(root);
            }
        }
        return results;
    }

    protected class FilenameSuffixFilter implements FilenameFilter {
        private String suffix;

        public FilenameSuffixFilter(String suffix) {
            this.suffix = suffix;
        }

        @Override
        public boolean accept(File file, String s) {
            return s.contains(suffix);
        }

    }

    protected String getPrefixRootPath(String arg) throws XMLParseException {
        String[] splitPaths = arg.split("\\/");
        if (splitPaths.length < 2)
            throw new XMLParseException("ViewResolver propertys in the prefix["
                    + arg + "] invalid!");
        return splitPaths[1];
    }
}
